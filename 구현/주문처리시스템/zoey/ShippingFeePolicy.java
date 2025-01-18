package com.example.shopping;

import java.math.BigDecimal;

public class ShippingFeePolicy extends PaymentFeePolicy {

    private ShippingType shippingType;

    public ShippingFeePolicy(PaymentPolicy paymentPolicy, ShippingType shippingType) {
        super(paymentPolicy);
        this.shippingType = shippingType;
    }

    @Override
    protected void calculateFee(Order order) {
        BigDecimal fee = shippingType.getFee();
        order.updatePaymentAmount(order.getPaymentAmount().add(fee));
        System.out.println("==== calculate payment fee : shippingType : " + shippingType + " shipping fee : " + fee);
    }
}
