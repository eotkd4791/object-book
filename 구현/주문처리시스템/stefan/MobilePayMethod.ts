import { PayMethod } from "./PayMethod";

export class MobilePayMethod implements PayMethod {
  pay(amount: number) {
    console.log(`휴대폰결제 ${amount}원 결제`);
  }
}
