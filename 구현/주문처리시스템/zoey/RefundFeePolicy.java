package com.example.shopping;


public abstract class RefundFeePolicy implements RefundPolicy {

    private RefundPolicy refundPolicy;
    protected boolean chargeFee;

    public RefundFeePolicy(RefundPolicy refundPolicy, boolean chargeFee) {
        this.refundPolicy = refundPolicy;
        this.chargeFee = chargeFee;
    }

    @Override
    public void refund(Order order) {
        System.out.println();
        System.out.println("===== calculate refund amount =====");
        calculateFee(order);
        refundPolicy.refund(order);
    }

    abstract protected void calculateFee(Order order);
}
