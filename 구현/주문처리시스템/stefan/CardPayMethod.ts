import { PayMethod } from "./PayMethod";

export class CardPayMethod implements PayMethod {
  pay(amount: number) {
    console.log(`카드결제 ${amount}원 결제`);
  }
}
