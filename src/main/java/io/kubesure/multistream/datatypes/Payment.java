package io.kubesure.multistream.datatypes;

@SuppressWarnings("unused")
public class Payment implements Comparable<Payment>{
    private String cif;
    private int amount;
    private String account;
    private Long timestamp;

    public Payment() {}

    public Payment(String cif,int amount, String account,Long timestamp){
        this.cif = cif;
        this.amount = amount;
        this.account = account;
        this.timestamp = timestamp;
    }

    public int compareTo(Payment payment){
           return Long.compare(this.timestamp, payment.timestamp); 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Payment amount - ")
          .append(amount)
          .append(" from customer - ")
          .append(cif)
          .append(" of amount - ")
          .append(amount);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        } else if (o != null && getClass() == o.getClass()) {
            Payment that = (Payment) o;
            return ( (this.cif.equals(that.cif)) && (this.timestamp.equals(that.timestamp)) ); 
        }
        return false;
    }
}