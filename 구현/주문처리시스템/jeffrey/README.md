## 객체

### Customer

- Cart에 Product 들을 담아서 주문을 하고 결제 / 취소 / 환불을 진행하는 주체
- Cart, Orders를 소유하고 있으며 Product, PaymentFactory, CustomError에 의존

### Product

- 상품의 정보를 담고있는 데이터 모델
- 이름, 가격, 수량, 배송비에 대한 정보 및 남은 재고에 대해서 제공.

### Products

- Product의 일급 컬렉션
- Product 리스트를 관리하며 가격, 수량, 배송비를 계산한 총합액을 제공.

### Cart

- 상품을 담고, 상품에 대한 상태를 관리하는 객체
- 상품 수량이 0개인 상품은 담지 않는다.
- product를 소유

### Order

- 카트에 담긴 상품들의 결제 금액과 주문 상태를 소유하고 주문 상태를 관리한다.
- Paymentable, Refundable, Products를 소유한다

### Orders

- Order의 일급 컬렉션
- Order 추가 및 특정 Order를 반환하는 기능 제공

### Paymentable

- 결제 수단의 추상화 인터페이스
- 결제를 진행한다

### Refundable

- 결제 환불의 추상화 인터페이스
- 환불을 진행한다

### PaymentFactory

- 결제 수단을 외부 모듈에서 내부 객체들을 알 수 없고 필요 정보로만 구현체를 만들어주는 Factory 객체

### CardPayment

- 카드번호를 소유하고, 카드로 결제 및 환불하는 프로세스를 담당하는 객체
- Paymentable 인터페이스를 채택하여 구현한 객체
- Refundable 인터페이스를 채택하여 구현한 객체

### AccountTransferPayment

- 계좌번호를 소유하고, 계좌이체를 통해 결제 및 환불하는 프로세스를 담당하는 객체
- Paymentable 인터페이스를 채택하여 구현한 객체
- Refundable 인터페이스를 채택하여 구현한 객체

### MobilePayment

- 휴대폰번호를 소유하고, 휴대폰 결제를 통해 결제 및 환불하는 프로세스를 담당하는 객체
- Paymentable 인터페이스를 채택하여 구현한 객체
- Refundable 인터페이스를 채택하여 구현한 객체

### CustomError

- 에러를 다루기 위한 객체
- 에러메시지를 사용자에게 보여주기 위한 역할


## 트레이드 오프

1. Cart를 추상화 해야하나?
  - 온라인 쇼핑몰이 아니라면 해당 인터페이스를 Cart에서 채택해서 사용하였을테지만,
온라인 쇼핑몰에서는 카트처럼 담는 역할을 하는 존재가 카트 외에 없기 때문에 추상화 없이 사용.
  ```swift
public protocol ProductContainable {
	var products: [Product] { get }
	var isEmpty: Bool { get }
	func add(_ products: Product ...)
	func clear()
}
   ```
2. Cart는 Product 배열과 Products 일급 컬렉션 중 어느 것을 소유해야하나?
    - Products는 일급 컬렉션으로 다루어지기에 Cart에서 Products를 소유하는 것이 더 좋아보임.
    - 하지만, Cart도 일급 컬렉션. 카트의 역할인 Product를 추가하고 제거하고 물량 확인에 적합
    - Products는 Order에서 사용되는 총합액 계산에 더 중점을 두어, Order에 전달 시에 Products 객체로 래핑.  
