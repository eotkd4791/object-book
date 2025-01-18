package com.example.shopping;

import java.math.BigDecimal;

public abstract class BasicRefundPolicy implements RefundPolicy {

    protected BigDecimal refundAmount;

    protected BasicRefundPolicy(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public void refund(Order order) {
        System.out.println();
        System.out.println("===== start refund =====");
        requestRefund(order);
        order.changeStatus(OrderStatus.REFUNDED);
        System.out.println("refund succeeded. change order status " + order);
    }


    protected abstract void requestRefund(Order order);
}
