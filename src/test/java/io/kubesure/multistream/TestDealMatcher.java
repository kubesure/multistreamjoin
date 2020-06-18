package io.kubesure.multistream;

public class TestDealMatcher {

    public static void main(String args[]) {
        PurchaseSource purchase = new PurchaseSource(10,100,1000l);
        Thread purchaseThread = new Thread(purchase);
        purchaseThread.start(); 
 
        PaymentSource payment = new PaymentSource(10,100,3000l);
        Thread paymentThread  = new Thread(payment);
         paymentThread.start();
    }
}