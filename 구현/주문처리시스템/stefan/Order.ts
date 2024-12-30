import { AccountPayMethod } from "./AccountPayMethod";
import { CardPayMethod } from "./CardPayMethod";
import { Cart } from "./Cart";
import { MobilePayMethod } from "./MobilePayMethod";
import { PayMethod } from "./PayMethod";
import { PayType } from "./PayType";

// 주문
export class Order {
  constructor(private readonly cart: Cart) {}

  public execute() {
    if (this.cart.isEmpty()) return;

    const totalPrice = this.cart.getTotalPrice();
    const payMethod = this.getPayMethod(PayType.CARD); // 사용자가 선택한 결제 수단에 따른 PayMethod를 인자로 넘김

    payMethod.pay(totalPrice);
  }

  private getPayMethod(payType: PayType): PayMethod {
    switch (payType) {
      case PayType.CARD:
        return new CardPayMethod();
      case PayType.ACCOUNT:
        return new AccountPayMethod();
      case PayType.MOBILE:
        return new MobilePayMethod();
    }
  }
}
