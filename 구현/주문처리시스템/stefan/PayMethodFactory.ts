import { AccountPayMethod } from "./AccountPayMethod";
import { CardPayMethod } from "./CardPayMethod";
import { MobilePayMethod } from "./MobilePayMethod";
import { PayMethod } from "./PayMethod";
import { PayType } from "./PayType";

// 생성 로직을 분리하기 위한 팩토리
export class PayMethodFactory {
  static create(payType: PayType): PayMethod {
    switch (payType) {
      case PayType.ACCOUNT:
        return new AccountPayMethod();
      case PayType.CARD:
        return new CardPayMethod();
      case PayType.MOBILE:
        return new MobilePayMethod();
    }
  }
}
