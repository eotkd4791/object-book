package com.example.shopping;


public class BankPaymentPolicy extends BasicPaymentPolicy {

    private String bank;
    private String accountNumber;

    public BankPaymentPolicy(String bank, String accountNumber) {
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    @Override
    protected void makePayment(Order order) {
        System.out.println("pay by bank amount : " + order.getPaymentAmount());
        System.out.println("request payment to bank : " + bank + ", accountNumber : " + accountNumber);
    }
}
