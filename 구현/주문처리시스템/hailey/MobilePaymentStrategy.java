public class MobilePaymentStrategy implements PaymentStrategy {
    @Override
    // pay 라는 공통 기능에대해 각 결제 수단별로 서로 다른 로직을 재공함으로써 확장성을 높임
    public void pay(int amount) {
        // 모바일 결제 로직
        System.out.println("모바일로 " + amount + "원 결제");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.Mobile;
    }
}
