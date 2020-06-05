package io.kubesure.multistream.datatypes;

public class Deal {
    private Purchase purchase;
    private Payment payment;

    public Deal(){}

    public Deal(Purchase purchase, Payment payment){
        this.purchase = purchase;
        this.payment = payment;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}