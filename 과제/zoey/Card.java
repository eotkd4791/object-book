package com.example.order;

import java.math.BigDecimal;

public class Card implements Pay {

    @Override
    public void makePayment(BigDecimal amount, String info) {
        System.out.printf("make payment by card. amount : %d, card number : %s", amount, info);
    }
}
