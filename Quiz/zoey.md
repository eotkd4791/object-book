# 오브젝트 최종 정리 및 퀴즈

## 1. 다음 코드를 보고 어떤 부분에 개선이 필요한지 역할, 책임 관점에서 코드를 평가해주세요.

```java
class Order {
    private double totalAmount;

    public Order(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class Invoice {
    public void generateInvoice(Order order) {
        System.out.println("Generating invoice for order amount: " + order.getTotalAmount());
    }
}

class OrderProcessor {
    private Invoice invoice = new Invoice();

    public void processOrder(Order order) {
        System.out.println("Processing order...");
        saveOrder(order);
        invoice.generateInvoice(order);
    }

    private void saveOrder(Order order) {
        System.out.println("Saving order to database...");
    }
}
```

## 2. 다음 코드를 상속과 다형성의 관점에서 평가해주세요.

```java
class Character {
    private String name;

    public Character(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + " attacks!");
    }
}

class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    public void useSword() {
        System.out.println("Using sword to attack!");
    }
}

class Mage extends Character {
    public Mage(String name) {
        super(name);
    }

    public void castSpell() {
        System.out.println("Casting a powerful spell!");
    }
}

class Game {
    public void startGame() {
        Character warrior = new Warrior("Warrior");
        Character mage = new Mage("Mage");

        warrior.attack();
        mage.attack();

        // 다운캐스팅을 이용한 서브타입 동작 호출
        if (warrior instanceof Warrior) {
            ((Warrior) warrior).useSword();
        }
        if (mage instanceof Mage) {
            ((Mage) mage).castSpell();
        }
    }
}
```

## 3. 다음 코드를 응집도와 결합도 관점에서 평가해주세요.

```java
class ShippingService {
    private String provider;

    public ShippingService(String provider) {
        this.provider = provider;
    }

    public void calculateShippingCost(double weight) {
        System.out.println("Calculating shipping cost using " + provider + " for weight: " + weight);
    }

    public void trackPackage(String trackingNumber) {
        System.out.println("Tracking package " + trackingNumber + " with " + provider);
    }
}

class Order {
    private double weight;
    private String trackingNumber;

    public Order(double weight, String trackingNumber) {
        this.weight = weight;
        this.trackingNumber = trackingNumber;
    }

    public double getWeight() {
        return weight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
}

class OrderProcessor {
    private ShippingService shippingService;

    public OrderProcessor(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void processOrder(Order order) {
        System.out.println("Processing order...");
        shippingService.calculateShippingCost(order.getWeight());
        shippingService.trackPackage(order.getTrackingNumber());
    }
}
```

## 4. 다음 코드를 캡슐화 관점에서 평가해주세요.

```java
interface PaymentMethod {
    void pay(double amount);
}

class CreditCard implements PaymentMethod {
    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPal implements PaymentMethod {
    private String email;

    public PayPal(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using PayPal account: " + email);
    }
}

class PaymentProcessor {
    public void processPayment(String type, double amount, String identifier) {
        if (type.equals("CreditCard")) {
            CreditCard creditCard = new CreditCard(identifier);
            creditCard.pay(amount);
        } else if (type.equals("PayPal")) {
            PayPal payPal = new PayPal(identifier);
            payPal.pay(amount);
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
```
