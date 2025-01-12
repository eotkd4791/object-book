import { PayMethod } from "./PayMethod";

export class CardPayMethod implements PayMethod {
  constructor(private readonly cardNumber: string) {}

  pay(amount: number) {
    console.log(`카드결제 ${this.cardNumber} - ${amount}원 결제`);
  }
}
