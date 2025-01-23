# 오브젝트 최종 정리 및 퀴즈

## 1. 다음 코드를 보고 어떤 부분에 개선이 필요한지 역할, 책임 관점에서 코드를 평가해주세요.

```swift
class Order {
    private let totalAmount: Double

    init(totalAmount: Double) {
        self.totalAmount = totalAmount
    }

    func getTotalAmount() -> Double {
        return totalAmount
    }
}

class Invoice {
    func generateInvoice(for order: Order) {
        print("Generating invoice for order amount: \(order.getTotalAmount())")
    }
}

class OrderProcessor {
    private let invoice = Invoice()

    func processOrder(order: Order) {
        print("Processing order...")
        saveOrder(order)
        invoice.generateInvoice(for: order)
    }

    private func saveOrder(_ order: Order) {
        print("Saving order to database...")
    }
}

```

## 2. 다음 코드를 상속과 다형성의 관점에서 평가해주세요.

```swift
class Character {
    let name: String

    init(name: String) {
        self.name = name
    }

    func attack() {
        print("\(name) attacks!")
    }
}

class Warrior: Character {
    func useSword() {
        print("Using sword to attack!")
    }
}

class Mage: Character {
    func castSpell() {
        print("Casting a powerful spell!")
    }
}

class Game {
    func startGame() {
        let warrior: Character = Warrior(name: "Warrior")
        let mage: Character = Mage(name: "Mage")

        warrior.attack()
        mage.attack()

        // 다운캐스팅을 이용한 서브타입 동작 호출
        if let warrior = warrior as? Warrior {
            warrior.useSword()
        }
        if let mage = mage as? Mage {
            mage.castSpell()
        }
    }
}

```

## 3. 다음 코드를 응집도와 결합도 관점에서 평가해주세요.

```swift
protocol PaymentMethod {
    func pay(amount: Double)
}

class CreditCard: PaymentMethod {
    private let cardNumber: String

    init(cardNumber: String) {
        self.cardNumber = cardNumber
    }

    func pay(amount: Double) {
        print("Paying \(amount) using Credit Card: \(cardNumber)")
    }
}

class PayPal: PaymentMethod {
    private let email: String

    init(email: String) {
        self.email = email
    }

    func pay(amount: Double) {
        print("Paying \(amount) using PayPal account: \(email)")
    }
}

class PaymentProcessor {
    func processPayment(type: String, amount: Double, identifier: String) {
        switch type {
        case "CreditCard":
            let creditCard = CreditCard(cardNumber: identifier)
            creditCard.pay(amount)
        case "PayPal":
            let payPal = PayPal(email: identifier)
            payPal.pay(amount)
        default:
            fatalError("Unknown payment type: \(type)")
        }
    }
}

```

## 4. 다음 코드를 캡슐화 관점에서 평가해주세요.

```swift
protocol PaymentMethod {
    func pay(amount: Double)
}

class CreditCard: PaymentMethod {
    private let cardNumber: String

    init(cardNumber: String) {
        self.cardNumber = cardNumber
    }

    func pay(amount: Double) {
        print("Paying \(amount) using Credit Card: \(cardNumber)")
    }
}

class PayPal: PaymentMethod {
    private let email: String

    init(email: String) {
        self.email = email
    }

    func pay(amount: Double) {
        print("Paying \(amount) using PayPal account: \(email)")
    }
}

class PaymentProcessor {
    func processPayment(type: String, amount: Double, identifier: String) {
        switch type {
        case "CreditCard":
            let creditCard = CreditCard(cardNumber: identifier)
            creditCard.pay(amount)
        case "PayPal":
            let payPal = PayPal(email: identifier)
            payPal.pay(amount)
        default:
            fatalError("Unknown payment type: \(type)")
        }
    }
}

```
