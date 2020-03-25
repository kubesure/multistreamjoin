package io.kubesure.multistream.datatypes;

@SuppressWarnings("unused")
public class Customer implements Comparable<Customer>{
    private String cif;
    private String account;
    private Long timestamp;

    public Customer() {}

    public Customer(String cif,String account, Long timestamp){
        this.cif = cif;
        this.account = account;
        this.timestamp = timestamp;
    }

    public int compareTo(Customer customer){
           return Long.compare(this.timestamp, customer.timestamp); 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Customer - ").append(cif).append(" Account - ").append(account);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        } else if (o != null && getClass() == o.getClass()) {
            Customer that = (Customer) o;
            return ( (this.cif.equals(that.cif)) && (this.timestamp.equals(that.timestamp)) ); 
        }
        return false;
    }
}