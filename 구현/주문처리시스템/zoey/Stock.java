package com.example.shopping;

public class Stock {

    private Integer quantity;

    protected Stock(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    protected Stock() {
        this.quantity = 0;
    }

    /**
     * 재고 수량이 있는지 검증하는 기능
     * @return 재고수량 > 0이면 true
     */
    protected boolean isQuantityAvailable() {
        return quantity != null && (quantity > 0);
    }
}
