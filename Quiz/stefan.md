# 오브젝트 최종 정리 및 퀴즈

## 1. 다음 코드를 보고 어떤 부분에 개선이 필요한지 역할, 책임 관점에서 코드를 평가해주세요.

## 2. 다음 코드를 상속과 다형성의 관점에서 평가해주세요.

## 3. 다음 코드를 응집도와 결합도 관점에서 평가해주세요.

## 4. 다음 코드를 캡슐화 관점에서 평가해주세요.

```ts
interface PaymentMethod {
  pay(amount: number): void;
}

class CreditCard implements PaymentMethod {
  constructor(private cardNumber: string) {}

  pay(amount: number): void {
    console.log(`Paying ${amount} using Credit Card: ${this.cardNumber}`);
  }
}

class PayPal implements PaymentMethod {
  constructor(private email: string) {}

  pay(amount: number): void {
    console.log(`Paying ${amount} using PayPal account: ${this.email}`);
  }
}

class PaymentProcessor {
  processPayment(type: string, amount: number, identifier: string): void {
    if (type === "CreditCard") {
      const creditCard = new CreditCard(identifier);
      creditCard.pay(amount);
    } else if (type === "PayPal") {
      const payPal = new PayPal(identifier);
      payPal.pay(amount);
    } else {
      throw new Error(`Unknown payment type: ${type}`);
    }
  }
}
```
