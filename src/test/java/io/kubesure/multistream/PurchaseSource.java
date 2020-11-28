package io.kubesure.multistream;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Purchase;
import io.kubesure.multistream.datatypes.Purchase.Builder;
import io.kubesure.multistream.util.TimeUtil;

public class PurchaseSource extends CommonThread<Purchase> implements Runnable {

    private boolean running = true;
    private long transactionID = 1;
    private int produce; 
    private long INTERVAL_TIME = 3000l;
    private static final Logger log = LoggerFactory.getLogger(PurchaseSource.class);

    public PurchaseSource(int produce, int transactionStartID, long withDelay){
        this.produce = produce;
        this.INTERVAL_TIME = withDelay;
        this.transactionID = transactionStartID;
    }

    public PurchaseSource(int produce, long withDelay){
        this.produce = produce;
        this.INTERVAL_TIME = withDelay;
    }

    @Override
    public void run() {
        while(running && produce != 0) {
            try {
                Purchase purchase = newPurchase("EN" + transactionID++);
                sendPayload(purchase,Purchase.class, "purchase");
                Thread.sleep(INTERVAL_TIME);
            } catch (InterruptedException txp) {
                log.error("Error sleeping thread", txp);    
            } catch (Exception e) {
                log.error("Error sending to kafka", e);
            }
            --produce;
        }
    }

    private Purchase newPurchase(String transactionID) {
        Builder builder = Purchase.newBuilder();
        builder.setBuySell("b")
        .setChannel("online")
        .setClientID("234567")
        .setPurchaseAmount("989")
        .setPurchaseCurrency("AED")
        .setRate(4.16f)
        .setRateCode("CUS")
        .setSaleAmount("238")
        .setSaleCurrency("EUR")
        .setTransactionID(transactionID)
        .setTransactionDate(new DateTime().getMillis());
        log.info("Purchase--" + builder.getTransactionID() + "--"+ TimeUtil.ISOString(builder.getTransactionDate()));
        return builder.build();
    }
}