package com.example.shopping;

import java.math.BigDecimal;

public class MobileRefundPolicy extends BasicRefundPolicy {

    private String telecoms;
    private String phoneNumber;

    public MobileRefundPolicy(BigDecimal refundAmount, String telecoms, String phoneNumber) {
        super(refundAmount);
        this.telecoms = telecoms;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void requestRefund(Order order) {
        System.out.println("request refund to telecoms : " +  telecoms + ", phone number : " + phoneNumber);
    }
}
