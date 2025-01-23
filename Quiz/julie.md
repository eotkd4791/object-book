# 오브젝트 최종 정리 및 퀴즈

## 1. 다음 코드를 보고 어떤 부분에 개선이 필요한지 역할, 책임 관점에서 코드를 평가해주세요.

```kotlin
class Order(private val totalAmount: Double) {
    fun getTotalAmount(): Double = totalAmount
}

class Invoice {
    fun generateInvoice(order: Order) {
        println("Generating invoice for order amount: ${order.getTotalAmount()}")
    }
}

class OrderProcessor {
    private val invoice = Invoice()

    fun processOrder(order: Order) {
        println("Processing order...")
        saveOrder(order)
        invoice.generateInvoice(order)
    }

    private fun saveOrder(order: Order) {
        println("Saving order to database...")
    }
}

```

## 2. 다음 코드를 상속과 다형성의 관점에서 평가해주세요.

```kotlin
open class Character(private val name: String) {
    open fun attack() {
        println("$name attacks!")
    }
}

class Warrior(name: String) : Character(name) {
    fun useSword() {
        println("Using sword to attack!")
    }
}

class Mage(name: String) : Character(name) {
    fun castSpell() {
        println("Casting a powerful spell!")
    }
}

class Game {
    fun startGame() {
        val warrior: Character = Warrior("Warrior")
        val mage: Character = Mage("Mage")

        warrior.attack()
        mage.attack()

        // 다운캐스팅을 이용한 서브타입 동작 호출
        if (warrior is Warrior) {
            warrior.useSword()
        }
        if (mage is Mage) {
            mage.castSpell()
        }
    }
}

```

## 3. 다음 코드를 응집도와 결합도 관점에서 평가해주세요.

```kotlin
class ShippingService(private val provider: String) {
    fun calculateShippingCost(weight: Double) {
        println("Calculating shipping cost using $provider for weight: $weight")
    }

    fun trackPackage(trackingNumber: String) {
        println("Tracking package $trackingNumber with $provider")
    }
}

class Order(val weight: Double, val trackingNumber: String)

class OrderProcessor(private val shippingService: ShippingService) {
    fun processOrder(order: Order) {
        println("Processing order...")
        shippingService.calculateShippingCost(order.weight)
        shippingService.trackPackage(order.trackingNumber)
    }
}

```

## 4. 다음 코드를 캡슐화 관점에서 평가해주세요.

```kotlin
interface PaymentMethod {
    fun pay(amount: Double)
}

class CreditCard(private val cardNumber: String) : PaymentMethod {
    override fun pay(amount: Double) {
        println("Paying $amount using Credit Card: $cardNumber")
    }
}

class PayPal(private val email: String) : PaymentMethod {
    override fun pay(amount: Double) {
        println("Paying $amount using PayPal account: $email")
    }
}

class PaymentProcessor {
    fun processPayment(type: String, amount: Double, identifier: String) {
        when (type) {
            "CreditCard" -> {
                val creditCard = CreditCard(identifier)
                creditCard.pay(amount)
            }
            "PayPal" -> {
                val payPal = PayPal(identifier)
                payPal.pay(amount)
            }
            else -> throw IllegalArgumentException("Unknown payment type: $type")
        }
    }
}
```
