package com.example.shopping;

import java.math.BigDecimal;

public class CardRefundPolicy extends BasicRefundPolicy {

    private String cardCompany;
    private String cardNumber;

    public CardRefundPolicy(final BigDecimal amount, final String cardCompany, final String cardNumber) {
        super(amount);
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
    }

    @Override
    protected void requestRefund(Order order) {
        System.out.println("request refund to card company : " + cardCompany + ", card number : " + cardNumber + "amount : " + refundAmount);
    }
}
