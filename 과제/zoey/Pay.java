package com.example.order;

import java.math.BigDecimal;

public interface Pay {

    /**
     * 총 결제 금액과 결제 유형에 따라 필요한 정보를 입력받아서 결제한다
     * @param amount 결제 금액
     * @param info 결제시 필요한 정보
     */
    void makePayment(BigDecimal amount, String info);
}
