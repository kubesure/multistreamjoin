package io.kubesure.multistream;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Payment.Builder;
import io.kubesure.multistream.util.TimeUtil;

public class PaymentSource extends CommonThread<Payment> implements Runnable {

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
                sendPayload(payment,Payment.class,"payment");
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
        Builder builder = Payment.newBuilder();
        builder.setAccount("122332")
        .setAmount(989f)
        .setClientID("234567")
        .setReferenceNumber("E32e3e")
        .setStatus("success")
        .setTransactionID(transactionID)
        .setTransactionDate(new DateTime().getMillis());
        log.info("Payment --"+builder.getTransactionID() + "--"+ TimeUtil.ISOString(builder.getTransactionDate()));
        return builder.build();
    }
}