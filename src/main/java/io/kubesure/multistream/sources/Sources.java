package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;

public class Sources {

    public static DataStream<Purchase> purchaseSource(StreamExecutionEnvironment env){

        DataStream<Purchase> purchases = env.addSource(new SourceFunction<Purchase>() {
            
            private static final long serialVersionUID = 7624720720188441164L;
            private volatile boolean running = true;

            @Override
            public void run(SourceContext<Purchase> sc) throws Exception {

                Purchase p = new Purchase();
                sc.collectWithTimestamp(p, 0);
                sc.emitWatermark(new Watermark(0));
                while (running) {
					Thread.sleep(1000);
				}
            }

            @Override
            public void cancel() {
                running = false;
            }
        });

        return purchases;
    }

    public static DataStream<Payment> paymentSource(StreamExecutionEnvironment env){

        DataStream<Payment> payments = env.addSource(new SourceFunction<Payment>() {
            private static final long serialVersionUID = -5325807883856682647L;
            private volatile boolean running = true;

            @Override
            public void run(SourceContext<Payment> sc) throws Exception {

                while (running) {
                    Payment p = new Payment();
                    sc.collectWithTimestamp(p, 0);
                    sc.emitWatermark(new Watermark(0));
					Thread.sleep(1000);
				}
            }

            @Override
            public void cancel() {
                running = false;
            }
        });

        return payments;
    }
}
