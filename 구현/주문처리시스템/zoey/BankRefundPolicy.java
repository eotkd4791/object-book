package com.example.shopping;

import java.math.BigDecimal;

public class BankRefundPolicy extends BasicRefundPolicy {

    private String bank;
    private String accountNumber;

    public BankRefundPolicy(BigDecimal refundAmount, String bank, String accountNumber) {
        super(refundAmount);
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    @Override
    protected void requestRefund(Order order) {
        System.out.println("request refund to bank : " +  bank + ", account number : " + accountNumber);
    }
}
