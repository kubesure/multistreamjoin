package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Payment;

public class PaymentSource extends RichParallelSourceFunction<Payment> {

    private static final long serialVersionUID = 6910755227184782112L;
    private static final Logger log = LoggerFactory.getLogger(PaymentSource.class);

    private boolean running = true;
    private boolean runOnce = false;
    private long delay = 500l;

    public PaymentSource(boolean runOnce){
        this.runOnce = runOnce;
    }

    public PaymentSource(boolean runOnce, long withDelay){
        this.runOnce = runOnce;
        this.delay = withDelay;
    }

    public PaymentSource(){}

    @Override
    public void run(SourceContext<Payment> ctx) throws Exception {

        // while(running) {
            Payment p1 = newPayment("EN1");
            ctx.emitWatermark(new Watermark(p1.getTransactionDate().getMillis()));
            ctx.collect(p1);

            Thread.sleep(delay);

            Payment p2 = newPayment("EN2");
            ctx.emitWatermark(new Watermark(p2.getTransactionDate().getMillis()));
            ctx.collect(p2);

            Thread.sleep(delay);

            Payment p3 = newPayment("EN3");
            ctx.emitWatermark(new Watermark(p3.getTransactionDate().getMillis()));
            ctx.collect(p3);

            Thread.sleep(delay);

            Payment p4 = newPayment("EN4");
            ctx.emitWatermark(new Watermark(p4.getTransactionDate().getMillis()));
            ctx.collect(p4);

            Thread.sleep(delay);

            //if (runOnce) {
            //    cancel();
            //}
        //}
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

    @Override
    public void cancel() {
        running = false;
    }
    
}