import { Cart } from "./Cart";
import { PayMethodFactory } from "./PayMethodFactory";
import { PayType } from "./PayType";

// 주문
export class Order {
  constructor(private readonly cart: Cart) {}

  public execute() {
    if (this.cart.isEmpty()) return;

    const payMethod = PayMethodFactory.create(PayType.CARD);
    const totalPrice = this.cart.getTotalPrice();
    const serialNumber = this.getSerialNumber();

    payMethod.pay(totalPrice, serialNumber);
  }

  private getSerialNumber() {
    return "사용자가_입력한_시리얼_넘버";
  }
}
