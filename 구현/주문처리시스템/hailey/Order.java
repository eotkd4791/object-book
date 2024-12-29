public class Order {
    private Card cart;
    private PaymentStrategy paymentStrategy;

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

        // 결제
        paymentStrategy.pay(shoppingCart.getTotalPrice());
    }
}
