package com.example.shopping;

public abstract class BasicPaymentPolicy implements PaymentPolicy {

    @Override
    public void pay(Order order) {
        System.out.println();
        System.out.println("===== start payment =====");
        makePayment(order);
        order.changeStatus(OrderStatus.PAID);
        System.out.println("payment succeeded. change order status to paid : " + order);
    }

    protected abstract void makePayment(Order order);
}
