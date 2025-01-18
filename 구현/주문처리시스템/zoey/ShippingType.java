package com.example.shopping;

import java.math.BigDecimal;

public enum ShippingType {

    STANDARD(3000, "Standard Shipping"),
    EXPRESS(5000, "Express Shipping"),
    FREE(0, "Free Shipping");

    private final BigDecimal fee;       // 배송비
    private final String type;  // 배송 유형 이름

    ShippingType(int fee, String type) {
        this.fee = BigDecimal.valueOf(fee);
        this.type = type;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
