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

        while(running) {
            Payment p = newPayment();
            ctx.emitWatermark(new Watermark(p.getTransactionDate().getMillis()));
            ctx.collect(p);
            Thread.sleep(delay);
        }

        if (runOnce) {
            cancel();
        }
        
    }

    private Payment newPayment() {
        Payment p = new Payment();
        p.setAccount("122332");
        p.setAmount(989f);
        p.setClientID("234567");
        p.setReferenceNumber("E32e3e");
        p.setStatus("success");
        p.setTransactionID("EN123");
        p.setTransactionDate(new DateTime());
        return p;
    }

    @Override
    public void cancel() {
        running = false;
    }
    
}