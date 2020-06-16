package io.kubesure.multistream.util;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Purchase;

public class PurchaseSource extends CommonThread implements Runnable {

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
                send(Convertor.convertPurchaseToJson(purchase), "purchase");
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
        Purchase p = new Purchase();
        p.setBuySell("b");
        p.setChannel("online");
        p.setClientID("234567");
        p.setPurchaseAmount("989");
        p.setPurchaseCurrency("AED");
        p.setRate(4.16f);
        p.setRateCode("CUS");
        p.setSaleAmount("238");
        p.setSaleCurrency("EUR");
        p.setTransactionID(transactionID);
        p.setTransactionDate(new DateTime());
        return p;
    }
}