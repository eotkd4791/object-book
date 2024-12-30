import { PayMethod } from "./PayMethod";

export class AccountPayMethod implements PayMethod {
  pay(amount: number) {
    console.log(`계좌이체 ${amount}원 결제`);
  }
}
