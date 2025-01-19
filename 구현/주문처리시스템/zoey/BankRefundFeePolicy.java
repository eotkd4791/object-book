package com.example.shopping;

import java.math.BigDecimal;

public class BankRefundFeePolicy extends RefundFeePolicy {

    private BigDecimal transferFee;

    public BankRefundFeePolicy(RefundPolicy refundPolicy, boolean chargeFee, BigDecimal transferFee) {
        super(refundPolicy, chargeFee);
        this.transferFee = transferFee;
    }

    @Override
    protected void calculateFee(Order order) {
        BigDecimal fee = chargeFee ? transferFee : BigDecimal.ZERO;
        order.updateRefundAmount(order.getRefundAmount().subtract(fee));
        System.out.println("refund information -> refundAmount : " + order.getRefundAmount() + ", refundFee : " + fee);
    }
}
