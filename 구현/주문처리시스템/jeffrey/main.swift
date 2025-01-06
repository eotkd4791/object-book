import Foundation

// MARK: - 공통 모듈
public enum CustomError: Error, LocalizedError {
    case emptyCart
    case soldOut
    case paymentFailed
    
    public var localizedDescription: String {
        switch self {
        case .soldOut: "재고가 소진되어 해당 상품을 담을 수 없습니다."
        case .emptyCart: "장바구니가 비어있어서 주문이 불가합니다./n장바구니에 상품을 담은 후 다시 시도해주세요."
        case .paymentFailed: "결제에 실패하였습니다. 다시 시도해주세요."
        }
    }
}

// MARK: - 고객 모듈
public struct Customer {
    private let cart = Cart()
    
    public func pick(products: Product ...) {
        products.forEach { product in
            self.pick(product: product)
        }
    }
    
    /// 고객이 솔드아웃인 상품을 고른 것이기에, 품절된 상품은 pick을 하지 않도록 처리.
    public func pick(product: Product) {
        cart.add(product)
        print(cart)
    }
    
    @MainActor
    public func order(by paymentMethod: PaymentFactory) async {
        do {
            let order = try Order(cart: cart)
            print(order)
            let payment = paymentMethod.make()
            try await payment.processPayment(amount: order.totalOrderAmount)
            order.completePayment()
            print(order)
            cart.clear()
        } catch let error as CustomError {
            print(error.localizedDescription)
        } catch {
            print(error)
        }
    }
}

// MARK: - 스토어 모듈
public struct Product {
    public let name: String
    public let price: Decimal
    /// 수량은 주문 내역 등에서 알 필요가 없기 때문에 internal로 설정.
    let quantity: Int

    public init(name: String, price: Decimal, quantity: Int) {
        self.name = name
        self.price = price
        self.quantity = quantity
    }
    
    public var hasStock: Bool {
        self.quantity > 0
    }
}

public final class Cart: CustomStringConvertible {
    public private(set) var products: [Product] = []
    
    public init() {}
    
    public var isEmpty: Bool {
        products.isEmpty
    }
    
    public func add(_ products: Product ...) {
        for product in products {
            guard product.hasStock else {
                print(CustomError.soldOut.localizedDescription)
                continue
            }
            self.products.append(product)
        }
    }

    public func clear() {
        products.removeAll()
    }
    
    public var description: String {
        "현재 카트에 담겨져 있는 항목" + products
            .map { product in
                "\n- 상품명: \(product.name), 가격: \(product.price), 수량: \(product.quantity) "
            }.joined()
    }
}


/// 주문 모듈
public final class Order: CustomStringConvertible {
    public enum Status {
        /// 주문 접수
        case orderRceived
        /// 결제 완료
        case paymentCompleted
    }

    public let totalOrderAmount: Decimal
    public private(set) var status: Status

    public init(cart: Cart) throws {
        guard cart.isEmpty == false else {
            throw CustomError.emptyCart
        }
        self.status = .orderRceived
        /// 총 주문 금액 계산 외에 주문 상품에 대해서 알아야 한다면, 속성으로 추가
        self.totalOrderAmount = cart.products.reduce(0) { partialResult, product in
            partialResult + product.price
        }
    }
    
    public func completePayment() {
        status = .paymentCompleted
    }
    
    public var description: String {
        switch status {
        case .orderRceived: "주문 접수"
        case .paymentCompleted: "결제 완료."
        }
    }
}

// MARK: - 결제 모듈
public protocol Paymentable {
    func processPayment(amount: Decimal) async throws
}

public struct CardPayment: Paymentable {
    let cardNumber: String
    
    public func processPayment(amount: Decimal) async throws {
        print("카드 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("카드 결제 진행 완료")
    }
}

public struct AccountTransferPayment: Paymentable {
    let accountNumber: String
    
    public func processPayment(amount: Decimal) async throws {
        print("계좌이체 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("계좌이체 결제 진행 완료")
    }
}

public struct MobilePayment: Paymentable {
    let mobileNumber: String
    
    public func processPayment(amount: Decimal) async throws {
        print("모바일 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("모바일 결제 진행 완료")
    }
}

public enum PaymentFactory {
    case card(cardNumber: String)
    case accountTransfer(accountNumber: String)
    case mobile(mobileNumber: String)
    
    public func make() -> any Paymentable {
        switch self {
        case .card(let cardNumber): CardPayment(cardNumber: cardNumber)
        case .accountTransfer(let accountNumber): AccountTransferPayment(accountNumber: accountNumber)
        case .mobile(let mobileNumber): MobilePayment(mobileNumber: mobileNumber)
        }
    }
}

// MARK: - 실행
Task {
    let customer = Customer()
    customer.pick(product: Product(name: "상품1", price: 10_000, quantity: 2))
    customer.pick(products: Product(name: "상품2", price: 300_000, quantity: 1),
                  Product(name: "상품3", price: 6_300, quantity: 10),
                  Product(name: "상품4", price: 134_000, quantity: 3),
                  Product(name: "상품5", price: 1_000, quantity: 5))
    await customer.order(by: .card(cardNumber: "123456"))
    
    customer.pick(products: Product(name: "상품7", price: 300_000, quantity: 1),
                  Product(name: "상품8", price: 6_000, quantity: 10),
                  Product(name: "상품9", price: 34_000, quantity: 3))
    await customer.order(by: .accountTransfer(accountNumber: "5427529572"))
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 10),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5))
    await customer.order(by: .mobile(mobileNumber: "01012345678"))
    
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5))
    await customer.order(by: .mobile(mobileNumber: "01012345678"))
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 0),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 0),
                  Product(name: "상품13", price: 1_000, quantity: 0))
    await customer.order(by: .mobile(mobileNumber: "01012345678"))
}

RunLoop.main.run()
