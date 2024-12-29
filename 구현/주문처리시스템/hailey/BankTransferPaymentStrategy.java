public class BankTransferPaymentStrategy implements PaymentStrategy {
    @Override
    // pay 라는 공통 기능에대해 각 결제 수단별로 서로 다른 로직을 재공함으로써 확장성을 높임
    public void pay(int amount) {
        // 계좌 이체 로직 (계좌 정보 검증, 이체 처리 등)
        System.out.println("계좌이체로 " + amount + "원 결제");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.BANK_TRANSFER;
    }
}
