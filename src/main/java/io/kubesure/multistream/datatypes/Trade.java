package io.kubesure.multistream.datatypes;

@SuppressWarnings("unused")
public class Trade implements Comparable<Trade>{
    private String cif;
    private int amount;
    private Long timestamp;

    public Trade() {}

    public Trade(String cif,int amount, Long timestamp){
        this.cif = cif;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getCif(){
        return cif;
    }

    public void setCif(String cif){
        this.cif = cif;
    }

    public int compareTo(Trade trade){
           return Long.compare(this.timestamp, trade.timestamp); 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Trade - ").append(cif).append(" amount - ").append(amount);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        } else if (o != null && getClass() == o.getClass()) {
            Trade that = (Trade) o;
            return ( (this.cif.equals(that.cif)) && (this.timestamp.equals(that.timestamp)) ); 
        }
        return false;
    }
}