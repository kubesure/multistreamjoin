package io.kubesure.multistream.util;

public class TestDealMatcher {

    public static void main(String args[]) {
        PurchaseSource purchase = new PurchaseSource();
        Thread purchaseThread = new Thread(purchase);
        purchaseThread.start();

        PaymentSource payment = new PaymentSource();
        Thread paymentThread = new Thread(payment);
        paymentThread.start();

    }
    
}