package com.example.shopping;

import java.math.BigDecimal;

public class BankRefundFeePolicy extends RefundFeePolicy {

    private BigDecimal transferFee;

    public BankRefundFeePolicy(RefundPolicy refundPolicy, boolean chargeFee, BigDecimal transferFee) {
        super(refundPolicy, chargeFee);
        this.transferFee = transferFee;
    }

    @Override
    protected BigDecimal calculateFee(Order order) {
        return chargeFee ? transferFee : BigDecimal.ZERO;
    }
}
