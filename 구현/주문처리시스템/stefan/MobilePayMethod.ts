import { PayMethod } from "./PayMethod";

export class MobilePayMethod implements PayMethod {
  constructor(private readonly mobileNumber: string) {}

  pay(amount: number) {
    console.log(`휴대폰결제 ${this.mobileNumber} - ${amount}원 결제`);
  }
}
