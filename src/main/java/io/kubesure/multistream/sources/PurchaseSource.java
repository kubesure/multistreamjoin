package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.joda.time.DateTime;

import io.kubesure.multistream.datatypes.Purchase;

public class PurchaseSource implements SourceFunction<Purchase> {

   
    private static final long serialVersionUID = -821842602548548856L;
    //private static final Logger log = LoggerFactory.getLogger(PurchaseSource.class);

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
            ctx.emitWatermark(new Watermark(p1.getTransactionDate().getMillis()));
            ctx.collect(p1);
            Thread.sleep(withDelay);
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

    @Override
    public void cancel() {
        produce = 0;
    }

}
    