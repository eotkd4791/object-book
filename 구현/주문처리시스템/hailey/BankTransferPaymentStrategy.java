public class BankTransferPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        // 계좌 이체 로직 (계좌 정보 검증, 이체 처리 등)
        System.out.println("계좌이체로 " + amount + "원 결제");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.Bank_Transfer;
    }
}
