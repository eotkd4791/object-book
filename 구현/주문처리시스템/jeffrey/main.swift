import Foundation

// MARK: - main
func payment() async {
    let customer = Customer()
    customer.pick(product: Product(name: "상품1", price: 10_000, quantity: 2, deliveryCharge: .normal))
    customer.pick(products: Product(name: "상품2", price: 300_000, quantity: 1),
                  Product(name: "상품3", price: 6_300, quantity: 10, deliveryCharge: .normal),
                  Product(name: "상품4", price: 134_000, quantity: 3, deliveryCharge: .quick),
                  Product(name: "상품5", price: 1_000, quantity: 5, deliveryCharge: .quick))
    await customer.order(by: .card(cardNumber: "123456", type: .masterCard))
    await customer.payment()
    
    customer.pick(products: Product(name: "상품7", price: 300_000, quantity: 1, deliveryCharge: .normal),
                  Product(name: "상품8", price: 6_000, quantity: 10),
                  Product(name: "상품9", price: 34_000, quantity: 3, deliveryCharge: .quick))
    await customer.order(by: .accountTransfer(accountNumber: "5427529572", bank: .ibk))
    await customer.payment()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 10, deliveryCharge: .normal),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5, deliveryCharge: .quick))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .lg))
    await customer.payment()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5, deliveryCharge: .normal))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .kt))
    await customer.payment()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 0),
                  Product(name: "상품11", price: 6_300, quantity: 0, deliveryCharge: .normal),
                  Product(name: "상품12", price: 14_000, quantity: 0),
                  Product(name: "상품13", price: 1_000, quantity: 0, deliveryCharge: .normal))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .skt))
    await customer.payment()
}

func cancel() async {
    let customer = Customer()
    
    /// 주문 취소 성공
    customer.pick(product: Product(name: "상품1", price: 10_000, quantity: 2, deliveryCharge: .normal))
    customer.pick(products: Product(name: "상품2", price: 300_000, quantity: 1),
                  Product(name: "상품3", price: 6_300, quantity: 10),
                  Product(name: "상품4", price: 134_000, quantity: 3),
                  Product(name: "상품5", price: 1_000, quantity: 5, deliveryCharge: .normal))
    await customer.order(by: .card(cardNumber: "123456", type: .masterCard))
    await customer.cancel()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 3, deliveryCharge: .normal),
                  Product(name: "상품13", price: 1_000, quantity: 5, deliveryCharge: .normal))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .kt))
    await customer.cancel()
    
    /// 주문 취소 불가
    customer.pick(products: Product(name: "상품7", price: 300_000, quantity: 1),
                  Product(name: "상품8", price: 6_000, quantity: 10, deliveryCharge: .normal),
                  Product(name: "상품9", price: 34_000, quantity: 3))
    await customer.order(by: .accountTransfer(accountNumber: "5427529572", bank: .ibk))
    await customer.payment()
    await customer.cancel()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 10),
                  Product(name: "상품12", price: 14_000, quantity: 3, deliveryCharge: .quick),
                  Product(name: "상품13", price: 1_000, quantity: 5))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .lg))
    await customer.payment()
    await customer.refund()
    await customer.cancel()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 0),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 0, deliveryCharge: .normal),
                  Product(name: "상품13", price: 1_000, quantity: 0))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .skt))
    await customer.cancel()
}

func refund() async {
    let customer = Customer()
    
    /// 환불 성공
    customer.pick(products: Product(name: "상품7", price: 300_000, quantity: 1),
                  Product(name: "상품8", price: 6_000, quantity: 10),
                  Product(name: "상품9", price: 34_000, quantity: 3, deliveryCharge: .normal))
    await customer.order(by: .accountTransfer(accountNumber: "5427529572", bank: .ibk))
    await customer.payment()
    await customer.refund()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1, deliveryCharge: .normal),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5, deliveryCharge: .normal))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .kt))
    await customer.payment()
    await customer.refund()
    
    /// 환불 불가
    customer.pick(product: Product(name: "상품1", price: 10_000, quantity: 2, deliveryCharge: .normal))
    customer.pick(products: Product(name: "상품2", price: 300_000, quantity: 1),
                  Product(name: "상품3", price: 6_300, quantity: 10, deliveryCharge: .quick),
                  Product(name: "상품4", price: 134_000, quantity: 3),
                  Product(name: "상품5", price: 1_000, quantity: 5))
    await customer.order(by: .card(cardNumber: "123456", type: .masterCard))
    await customer.refund()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 1),
                  Product(name: "상품11", price: 6_300, quantity: 10),
                  Product(name: "상품12", price: 14_000, quantity: 3),
                  Product(name: "상품13", price: 1_000, quantity: 5, deliveryCharge: .quick))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .lg))
    await customer.cancel()
    await customer.refund()
    
    customer.pick(products: Product(name: "상품10", price: 300_000, quantity: 0),
                  Product(name: "상품11", price: 6_300, quantity: 0),
                  Product(name: "상품12", price: 14_000, quantity: 0, deliveryCharge: .normal),
                  Product(name: "상품13", price: 1_000, quantity: 0))
    await customer.order(by: .mobile(mobileNumber: "01012345678", telecommunication: .skt))
    await customer.payment()
    await customer.refund()
}

Task {
    await payment()
    await cancel()
    await refund()
}

RunLoop.main.run()
