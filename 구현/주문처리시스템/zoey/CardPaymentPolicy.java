package com.example.shopping;


public class CardPaymentPolicy extends BasicPaymentPolicy {

    private String cardCompany;
    private String cardNumber;

    public CardPaymentPolicy(String cardCompany, String cardNumber) {
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
    }

    @Override
    protected void makePayment(Order order) {
        System.out.println("pay by cardPayment amount : " + order.getPaymentAmount());
        System.out.println("request payment to cardCompany : " + cardCompany + ", cardNumber : " + cardNumber);
    }
}
