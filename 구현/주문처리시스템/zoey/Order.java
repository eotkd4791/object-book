package com.example.shopping;


import java.math.BigDecimal;
import java.util.Map;

public class Order {
    private Customer customer;
    private Map<Product, Integer> products;
    private BigDecimal totalAmount;
    private BigDecimal paymentAmount;
    private BigDecimal refundAmount;
    private OrderStatus status;
    private PaymentPolicy paymentPolicy; // 합성
    private RefundPolicy refundPolicy;

    public Order(Customer customer, Map<Product, Integer> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("products cannot be null or empty");
        }
        this.customer = customer;
        this.products = products;
        this.totalAmount = calculateTotalAmount(products);
        this.status = OrderStatus.PENDING;
    }

    // setPaymentPolicy 메서드로 PaymentPolicy 인스턴스에 대한 의존성을 런타임에 주입받는다.
    public void setPaymentPolicy(PaymentPolicy paymentPolicy) {
        if (paymentPolicy == null) {
            throw new IllegalArgumentException("paymentPolicy cannot be null");
        }
        this.paymentPolicy = paymentPolicy;
    }

    private BigDecimal calculateTotalAmount(Map<Product, Integer> products) {
        return products.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculatePrice(entry.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void changeStatus(OrderStatus status) {
        this.status = status;
    }

    public void pay() {
        paymentAmount = totalAmount;
        paymentPolicy.pay(this);
    }

    public void cancel() {
        if (isCancelable()) {
            status = OrderStatus.CANCELLED;
        }
    }

    private boolean isCancelable() {
        return status == OrderStatus.PENDING;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void updatePaymentAmount(BigDecimal amount) {
        this.paymentAmount = amount;
    }

    public void setRefundPolicy(RefundPolicy refundPolicy) {
        if (refundPolicy == null) {
            throw new IllegalArgumentException("refundPolicy cannot be null");
        }
        this.refundPolicy = refundPolicy;
    }

    public void refund() {
        this.refundAmount = totalAmount;
        refundPolicy.refund(this);
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void updateRefundAmount(BigDecimal amount) {
        refundAmount = amount;
    }

    @Override
    public String toString() {
        return "\n" +" * 주문내역 => " +
                "상태 : [" + status + "]" +
                ", 총금액=" + totalAmount +
                ", 결제금액=" + paymentAmount +
                ", 환불금액=" + refundAmount;
    }
}

