// 10장 코드의 재사용 관점에서 결제 수단 별로 공통적으로 반복되는 pay 라는 기능의 interface 를 사용함으로 코드의 재사용을 줄임
public interface PaymentStrategy {
    void pay(int amount);
    PaymentMethod getPaymentMethod();
}
