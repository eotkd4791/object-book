package com.example.shopping;

import java.math.BigDecimal;

public abstract class Payment implements Pay {

    private BigDecimal amount;

    protected Payment(BigDecimal amount) {
        this.amount = amount;
    }

    class CardPayment extends Payment {
        private String cardCompany;
        private String cardNumber;

        public CardPayment(BigDecimal amount, String cardCompany, String cardNumber) {
            super(amount);
            this.cardCompany = cardCompany;
            this.cardNumber = cardNumber;
        }

        @Override
        public void makePayment() {
            System.out.printf("make payment by card. amount : %s, cardCompany : %s, cardNumber : %s", amount, cardCompany, cardNumber);
        }

        @Override
        public void refund() {
            System.out.printf("refund by card. amount : %s, cardCompany : %s, cardNumber : %s", amount, cardCompany, cardNumber);
        }
    }

    class BankPayment extends Payment {
        private String bank;
        private String accountNumber;

        public BankPayment(BigDecimal amount, String bank, String accountNumber) {
            super(amount);
            this.bank = bank;
            this.accountNumber = accountNumber;
        }

        @Override
        public void makePayment() {
            System.out.printf("make payment by bank transfer. amount : %s, bank : %s, account number : %s", amount, bank, accountNumber);
        }

        @Override
        public void refund() {
            System.out.printf("refund by bank transfer. amount : %s, bank : %s, account number : %s", amount, bank, accountNumber);
        }
    }

    class MobilePayment extends Payment {
        private String telecoms;
        private String phoneNumber;

        public MobilePayment(BigDecimal amount, String telecoms, String phoneNumber) {
            super(amount);
            this.telecoms = telecoms;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void makePayment() {
            System.out.printf("make payment by mobilePayment. amount : %s, telecoms : %s, phoneNumber : %s", amount, telecoms, phoneNumber);
        }

        @Override
        public void refund() {
            System.out.printf("refund by mobilePayment. amount : %s, telecoms : %s, phoneNumber : %s", amount, telecoms, phoneNumber);
        }
    }
}
