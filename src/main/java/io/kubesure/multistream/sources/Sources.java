package io.kubesure.multistream.sources;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext;
import org.apache.flink.streaming.api.watermark.Watermark;

import io.kubesure.multistream.datatypes.Customer;
import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Trade;

public class Sources {

    public static DataStream<Customer> customerSource(StreamExecutionEnvironment env){

        DataStream<Customer> customers = env.addSource(new SourceFunction<Customer>() {
            private volatile boolean running = true;

            @Override
            public void run(SourceContext<Customer> sc) throws Exception {

                sc.collectWithTimestamp(new Customer("001", "ABC", 0L), 0);
                sc.emitWatermark(new Watermark(0));
                Thread.sleep(2000);
                sc.collectWithTimestamp(new Customer("002", "DEF", 500L), 500);
                sc.emitWatermark(new Watermark(500));
                Thread.sleep(1000);
                sc.collectWithTimestamp(new Customer("003", "GHI", 1500L), 1500);
                sc.emitWatermark(new Watermark(1500));
                Thread.sleep(3000);
                sc.collectWithTimestamp(new Customer("004", "JKL", 2000L), 2000);
                sc.emitWatermark(new Watermark(2000));
                Thread.sleep(3000);

                while (running) {
					Thread.sleep(1000);
				}
            }

            @Override
            public void cancel() {
                running = false;
            }
        });

        return customers;
    }

    public static DataStream<Trade> tradeSource(StreamExecutionEnvironment env){

        DataStream<Trade> trades = env.addSource(new SourceFunction<Trade>() {
            private volatile boolean running = true;

            @Override
            public void run(SourceContext<Trade> sc) throws Exception {

                sc.collectWithTimestamp(new Trade("001", 100, 1000L), 1000);
                sc.emitWatermark(new Watermark(0));
                Thread.sleep(1000);
                sc.collectWithTimestamp(new Trade("002", 300, 1200L), 1200);
                sc.emitWatermark(new Watermark(500));
                Thread.sleep(2000);
                sc.collectWithTimestamp(new Trade("003", 500, 1500L), 1500);
                sc.emitWatermark(new Watermark(1500));
                Thread.sleep(2000);
                sc.collectWithTimestamp(new Trade("004", 700, 2000L), 2000);
                sc.emitWatermark(new Watermark(2000));
                Thread.sleep(1000);

                while (running) {
					Thread.sleep(1000);
				}
            }

            @Override
            public void cancel() {
                running = false;
            }
        });

        return trades;
    }

    public static DataStream<Payment> paymentSource(StreamExecutionEnvironment env){

        DataStream<Payment> payments = env.addSource(new SourceFunction<Payment>() {
            private volatile boolean running = true;

            @Override
            public void run(SourceContext<Payment> sc) throws Exception {

                sc.collectWithTimestamp(new Payment("001", 100, "ABC",1100L), 1100);
                sc.emitWatermark(new Watermark(0));
                Thread.sleep(1000);
                sc.collectWithTimestamp(new Payment("002", 300, "DEF" ,1400L), 1400);
                sc.emitWatermark(new Watermark(500));
                Thread.sleep(2000);
                sc.collectWithTimestamp(new Payment("003", 500, "GHI",1600L), 1600);
                sc.emitWatermark(new Watermark(1500));
                Thread.sleep(2000);
                sc.collectWithTimestamp(new Payment("004", 700, "JKL" ,2000L), 2000);
                sc.emitWatermark(new Watermark(2000));
                Thread.sleep(1000);

                while (running) {
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
