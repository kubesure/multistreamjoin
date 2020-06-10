package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Purchase;

public class PurchaseSource extends RichParallelSourceFunction<Purchase> {

   
    private static final long serialVersionUID = -821842602548548856L;
    private static final Logger log = LoggerFactory.getLogger(PurchaseSource.class);

    private boolean running = true;
    private boolean runOnce = false;
    private long delay = 500l;

    public PurchaseSource(boolean runOnce, long withDelay) {
        this.runOnce = runOnce;
        this.delay = withDelay;
    }

    public PurchaseSource(boolean runOnce){
        this.runOnce = runOnce;
    }

    public PurchaseSource(){}

    @Override
    public void run(SourceContext<Purchase> ctx) throws Exception {

        //while(running) {
            
            Purchase p1 = newPurchase("EN1");
            ctx.emitWatermark(new Watermark(p1.getTransactionDate().getMillis()));
            ctx.collect(p1);

            Thread.sleep(delay);

            Purchase p2 = newPurchase("EN2");
            ctx.emitWatermark(new Watermark(p2.getTransactionDate().getMillis()));
            ctx.collect(p2);

            Thread.sleep(delay);

            Purchase p3 = newPurchase("EN3");
            ctx.emitWatermark(new Watermark(p3.getTransactionDate().getMillis()));
            ctx.collect(p3);

            Thread.sleep(delay);

            Purchase p4 = newPurchase("EN4");
            ctx.emitWatermark(new Watermark(p4.getTransactionDate().getMillis()));
            ctx.collect(p4);

            Thread.sleep(delay);

            //if (runOnce) {
                //cancel();
            //}
        //}
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

    @Override
    public void cancel() {
        running = false;    
    }

}
    