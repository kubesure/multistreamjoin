package io.kubesure.multistream.datatypes;

import org.joda.time.DateTime;

public class Payment {

    private String transactionID;
    private DateTime transactionDate;
    private String clientID;
    private String status;
    private String account;
    private Float amount;
    private String referenceNumber;

    public Payment() {}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Payment amount - ")
          .append(amount)
          .append(" from customer - ")
          .append(clientID)
          .append(" of amount - ")
          .append(amount);
        return sb.toString();
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public long getEventTime(){
        return this.transactionDate.getMillis();
    }
}