package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Purchase;
import io.kubesure.multistream.datatypes.Purchase.Builder;
import io.kubesure.multistream.util.TimeUtil;

public class PurchaseSource implements SourceFunction<Purchase> {

   
    private static final long serialVersionUID = -821842602548548856L;
    private static final Logger log = LoggerFactory.getLogger(PurchaseSource.class);

    private long withDelay = 500l;
    private int produce;
    private long transactionID = 1;

    public PurchaseSource(int produce, int transactionStartID, long withDelay){
        this.produce = produce;
        this.withDelay = withDelay;
        this.transactionID = transactionStartID;
    }

    public PurchaseSource(int produce, long withDelay){
        this.produce = produce;
        this.withDelay = withDelay;
    }

    public PurchaseSource(){}

    @Override
    public void run(SourceContext<Purchase> ctx) throws Exception {

        while(produce != 0) {
            Purchase p1 = newPurchase("EN" + transactionID++);
            ctx.emitWatermark(new Watermark(p1.getTransactionDate()));
            ctx.collect(p1);
            Thread.sleep(withDelay);
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
        log.debug(TimeUtil.ISOString(builder.getTransactionDate()));
        return builder.build();
    }

    @Override
    public void cancel() {
        produce = 0;
    }

}
    