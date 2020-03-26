package io.kubesure.multistream.datatypes;

public class JoinedEvents {
    private Customer customer;
    private Trade trade;
    private Payment payment;

    public JoinedEvents() {}

    public void setPayment(Payment payment){
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTrade(Trade trade){
        this.trade = trade;
    }

    public Trade getTrade() {
        return trade;
    }

    public String toString(){
          StringBuilder sb = new StringBuilder();
          if (customer == null) {
              sb.append("null customer");  
          } else {
              sb.append("customer enriched ")
              .append(customer.getCif())
              .append(" with payment ")
              .append(payment.getAmount());
          }
          return sb.toString();  
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o != null && getClass() == o.getClass()) {
            JoinedEvents that = (JoinedEvents) o;
            boolean isTradeEquals = this.trade.equals(that.trade);
            boolean isCustomerEquals = this.customer.equals(that.customer);
            boolean isPaymentEquals = this.payment.equals(that.payment);
            if (isCustomerEquals && isTradeEquals && isPaymentEquals) {
                return true;
            }
        } 
        return false;
    }
}