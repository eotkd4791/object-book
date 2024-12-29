public interface PaymentStrategy {
    void pay(int amount);
    PaymentMethod getPaymentMethod();
}
