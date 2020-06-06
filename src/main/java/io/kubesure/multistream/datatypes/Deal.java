package io.kubesure.multistream.datatypes;

import java.io.Serializable;

public class Deal implements Serializable{
   
    private static final long serialVersionUID = 4694386434588446195L;
    
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


    public String toString(){
        return new StringBuilder()
        .append("Deal - ")
        .append(purchase.getTransactionID())
        .append(" Executed - ")
        .append(payment.getStatus()).toString();
    }
}