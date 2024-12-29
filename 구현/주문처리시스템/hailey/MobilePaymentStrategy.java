public class MobilePaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        // 모바일 결제 로직
        System.out.println("모바일로 " + amount + "원 결제");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.Mobile;
    }
}
