import { PayMethod } from "./PayMethod";

export class AccountPayMethod implements PayMethod {
  constructor(private readonly accountNumber: string) {}

  pay(amount: number) {
    console.log(`계좌이체 ${this.accountNumber} - ${amount}원 결제`);
  }
}
