package com.example.shopping;


public class MobilePaymentPolicy extends BasicPaymentPolicy {

    private String telecoms;
    private String phoneNumber;

    public MobilePaymentPolicy(String telecoms, String phoneNumber) {
        this.telecoms = telecoms;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void makePayment(Order order) {
        System.out.println("pay by mobile amount : " + order.getPaymentAmount());
        System.out.println("request payment to telecoms : " + telecoms + ", phoneNumber : " + phoneNumber);
    }
}
