## 객체

### Customer

- Cart에 Product 들을 담아서 주문을 하고 결제를 진행하는 주체
- Cart를 소유하고 있으며 Product, Order, Paymentable에 의존

### Product

- 상품의 정보를 담고있는 데이터 모델

### Cart

- 상품을 담고, 상품에 대한 상태를 관리하는 객체
- 상품 수량이 0개인 상품은 담지 않는다.
- product를 소유

### Order

- 카트에 담긴 상품들의 결제 금액과 주문 상태를 소유하고 주문 상태를 관리한다.
- Cart, Product에 의존한다

### Paymentable

- 결제 수단의 추상화 인터페이스
- 결제를 진행한다

### PaymentFactory

- 결제 수단을 외부 모듈에서 내부 객체들을 알 수 없고 필요 정보로만 구현체를 만들어주는 Factory 객체

### CardPayment

- 카드번호를 소유하고, 카드로 결제하는 프로세스를 담당하는 객체
- paymentable 인터페이스를 채택하여 구현한 객체

### AccountTransferPayment

- 계좌번호를 소유하고, 계좌이체를 통해 결제하는 프로세스를 담당하는 객체
- paymentable 인터페이스를 채택하여 구현한 객체

### MobilePayment

- 휴대폰번호를 소유하고, 휴대폰 결제를 통해 결제하는 프로세스를 담당하는 객체
- paymentable 인터페이스를 채택하여 구현한 객체

### CustomError

- 에러를 다루기 위한 객체
- 에러메시지를 사용자에게 보여주기 위한 역할


## 트레이드 오프

1. Order는 Product를 모르는 것이 나을까?
  - 만약 Product를 몰라도 된다면, Order에서 총 금액을 계산하지 않고, Cart가 총 금액을 계산하는 책임을 갖게 한다면 의존 관계 제거 가능.
  - 대부분의 쇼핑몰은 주문한 상품들을 주문 내역에서 보여줘야 해서, 추후 확장성을 고려하면 Product도 의존 관계 형성 필요하다고 생각하여 Order에서 Product를 의존하는 방안으로 진행
2. 장바구니 수량을 확인하는 것은 Cart, Customer 중 어느 객체한테 주는 것이 맞을까?
  - Customer -> 상품을 선택하는 주체이기에, 더 자연스러움
  - Cart -> 상품 상태 관리 및 검증을 담당. 이후에 해당 상품이 품절되면 해당 객체에서만 처리하면 됨.
  - 온라인 쇼핑몰이 아니였다면 Customer. 온라인 쇼핑몰은 Cart에 해당 책임을 부여하는 것이 이후 확장성을 고려했을 때 더 좋을 것으로 판단하여 해당 방안으로 진행
3. Cart를 추상화 해야하나?
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