package com.example.shopping;

import java.math.BigDecimal;

public class ShoppingMain {

    public static void main(String[] args) {
        Customer customer = new Customer("zoey", "zoey@email.com", "seoul, gangname 124st 6F", "821012345678");
        Product iphone = new Product("iphone", BigDecimal.valueOf(2000000), 30);
        Product macbook = new Product("macbook", BigDecimal.valueOf(4000000), 10);
        // 1. 장바구니에 물건 담기
        customer.addCart(iphone);
        customer.addCart(macbook);

        // 2. 결제
        Order order = new Order(customer, customer.getShoppingCart());
        System.out.println("order : " + order);

        order.setPaymentPolicy(
                new ShippingFeePolicy(
                    new BankPaymentPolicy("WOORI", "1003955774930"),
                            ShippingType.EXPRESS)
        );
        order.pay();

        // 3. 환불
        order.setRefundPolicy(
                new BankRefundFeePolicy(
                        new BankRefundPolicy(order.getRefundAmount(), "SHINHAN", "456398475"),
                        true,
                        BigDecimal.valueOf(500)
                )
        );
        order.refund();
    }
}
