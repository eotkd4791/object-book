// 11장 합성과 유연한 설계에 따라 결제라는 Order 클래스는 코드의 변화 없이 결제수단의 변화에 유연하게 대처가능 함
public class Order {
    private Card cart;
    private PaymentStrategy paymentStrategy;

    // 결제 수단 선택
    // 9장 개방 폐쇄 원칙에 따라 다른 결제 수단이 추가되는 경우 해당 코드만 추가되면 다른 코드의 변화없이 결제 수단의 추가가 가능함
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.CARD) {
            paymentStrategy = new CardPaymentStrategy();
        } else if (paymentMethod == PaymentMethod.BANK_TRANSFER) {
            paymentStrategy = new BankTransferPaymentStrategy();
        }  else if (paymentMethod == PaymentMethod.Mobile) {
            paymentStrategy = new MobilePaymentStrategy();
        }else {
            throw new IllegalArgumentException("지원하지 않는 결제 방식입니다.");
        }
    }
  
    public void placeOrder() {
        if (cart.isEmpty()) {
            throw new EmptyShoppingCartException("장바구니가 비어있습니다.");
        }

        // 결제 수단에 따른 결제 진행
        paymentStrategy.pay(shoppingCart.getTotalPrice());
    }
}
