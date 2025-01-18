package com.example.shopping;


public interface Pay {

    /**
     * 총 결제 금액과 결제 유형에 따라 필요한 정보를 입력받아서 결제한다
     */
    void makePayment();

    void refund();
}
