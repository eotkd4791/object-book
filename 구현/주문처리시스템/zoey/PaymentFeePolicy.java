package com.example.shopping;

public abstract class PaymentFeePolicy implements PaymentPolicy {

    private PaymentPolicy paymentPolicy;

    public PaymentFeePolicy(PaymentPolicy paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }

    @Override
    public void pay(Order order) {
        calculateFee(order);
        paymentPolicy.pay(order);
    }

    abstract protected void calculateFee(Order order);
}
