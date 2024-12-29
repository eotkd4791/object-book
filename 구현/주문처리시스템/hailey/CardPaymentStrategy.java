public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        // 카드 결제 로직 (카드 정보 검증, 승인 요청 등)
        System.out.println("카드로 " + amount + "원 결제");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CardPayment;
    }
}
