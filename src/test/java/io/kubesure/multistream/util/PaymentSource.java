package io.kubesure.multistream.util;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Payment;

public class PaymentSource extends CommonThread implements Runnable {

    private boolean running = true;
    private long transactionID = 1;
    private int produce; 
    private static final Logger log = LoggerFactory.getLogger(PaymentSource.class);

    public PaymentSource(){}

    public PaymentSource(int produce){
        this.produce = produce;
    }

    @Override
    public void run() {
        while(running) {
            try {
                Payment payment = newPayment("EN" + transactionID++);
                send(Convertor.convertPaymentToJson(payment), "payment");
            } catch (Exception e) {
                log.error("Error sending payment to kafka", e);
            }
        }
    }

    private Payment newPayment(String transactionID) {
        Payment p = new Payment();
        p.setAccount("122332");
        p.setAmount(989f);
        p.setClientID("234567");
        p.setReferenceNumber("E32e3e");
        p.setStatus("success");
        p.setTransactionID(transactionID);
        p.setTransactionDate(new DateTime());
        return p;
    }
    
}