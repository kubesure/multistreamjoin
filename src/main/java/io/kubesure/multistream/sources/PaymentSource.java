package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Payment.Builder;
import io.kubesure.multistream.util.TimeUtil;

public class PaymentSource implements SourceFunction<Payment> {

    private static final long serialVersionUID = 6910755227184782112L;
    private static final Logger log = LoggerFactory.getLogger(PaymentSource.class);

    private long withDelay = 500l;
    private long transactionID = 1;
    private int produce;

    public PaymentSource(int produce,long withDelay){
        this.produce = produce;
        this.withDelay = withDelay;
    }

    public PaymentSource(int produce,int transactionStartID,long withDelay){
        this.produce = produce;
        this.withDelay = withDelay;
        this.transactionID = transactionStartID;
    }

    @Override
    public void run(SourceContext<Payment> ctx) throws Exception {

        while(produce !=0) {
            Payment p1 = newPayment("EN" + transactionID++);
            ctx.emitWatermark(new Watermark(p1.getTransactionDate()));
            ctx.collect(p1);
            Thread.sleep(withDelay);
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
        log.debug(TimeUtil.ISOString(builder.getTransactionDate()));
        return builder.build();
    }

    @Override
    public void cancel() {
        produce = 0;
    }
}