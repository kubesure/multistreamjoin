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

    boolean running = true;
    boolean runOnce = false;
    long delay = 500l;

    public PurchaseSource(boolean runOnce, long withDelay) {
        this.runOnce = runOnce;
        this.delay = withDelay;
    }

    public PurchaseSource(){}

    @Override
    public void run(SourceContext<Purchase> ctx) throws Exception {

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
        p.setTransactionID("EN123");
        p.setTransactionDate(new DateTime());
        ctx.collect(p);
        ctx.emitWatermark(new Watermark(p.getTransactionDate().getMillis()));
        
        while(running) {
            Thread.sleep(delay);
        }

        if (runOnce) {
            cancel();
        }

    }

    @Override
    public void cancel() {
        running = false;    
    }

}
    