package io.kubesure.multistream;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.util.Convertor;

public class PaymentSource extends CommonThread implements Runnable {

    private boolean running = true;
    private long transactionID = 1;
    private int produce; 
    private long INTERVAL_TIME = 3000l;
    private static final Logger log = LoggerFactory.getLogger(PaymentSource.class);

    public PaymentSource(int produce,long withDelay){
        this.produce = produce;
        this.INTERVAL_TIME = withDelay;
    }

    public PaymentSource(int produce,int transactionStartID,long withDelay){
        this.produce = produce;
        this.INTERVAL_TIME = withDelay;
        this.transactionID = transactionStartID;
    }

    @Override
    public void run() {
        while(running && produce !=0) {
            try {
                Payment payment = newPayment("EN" + transactionID++);
                send(Convertor.convertPaymentToJson(payment), "payment");
                Thread.sleep(INTERVAL_TIME);
            } catch (InterruptedException txp) {
                log.error("Error sleeping thread", txp);  
            }   
            catch (Exception e) {
                log.error("Error sending payment to kafka", e);
            }
            --produce;
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