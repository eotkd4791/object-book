package com.example.shopping;

import java.math.BigDecimal;

public abstract class RefundFeePolicy implements RefundPolicy {

    private RefundPolicy refundPolicy;
    protected boolean chargeFee;

    public RefundFeePolicy(RefundPolicy refundPolicy, boolean chargeFee) {
        this.refundPolicy = refundPolicy;
        this.chargeFee = chargeFee;
    }

    @Override
    public void refund(Order order) {
        System.out.println();
        System.out.println("===== calculate refund amount =====");
        BigDecimal fee = calculateFee(order);
        order.updateRefundAmount(fee);
        System.out.println("refund information -> refundAmount : " + order.getRefundAmount() + ", refundFee : " + fee);
        refundPolicy.refund(order);
    }

    abstract protected BigDecimal calculateFee(Order order);
}
