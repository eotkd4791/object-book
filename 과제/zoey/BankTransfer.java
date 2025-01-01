package com.example.order;

import java.math.BigDecimal;

public class BankTransfer implements Pay {

    @Override
    public void makePayment(BigDecimal amount, String info) {
        System.out.printf("make payment by bank transfer. amount : %d, account number : %s", amount, info);
    }
}
