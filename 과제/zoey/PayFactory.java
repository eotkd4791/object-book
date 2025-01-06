package com.example.order;

public class PayFactory {

    public static Pay getPayMethod(PayType payType) {
        return switch (payType) {
            case CARD -> new Card();
            case BANK_TRANSFER -> new BankTransfer();
            case MOBILE -> new MobilePayment();
            default -> throw new IllegalArgumentException("not supported pay type");
        };
    }
}
