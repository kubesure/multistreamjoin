package io.kubesure.multistream.job;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Deal;
import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;

public class EventTimeJoin extends KeyedCoProcessFunction<String, Purchase, Payment, Deal> {

    private static final long serialVersionUID = 13434343455656L;
    private ValueState<Purchase> purchaseState;
    private ValueState<Payment> paymentState;

    private static final Logger log = LoggerFactory.getLogger(EventTimeJoin.class);

    public EventTimeJoin() {}

    @Override
    public void open(Configuration config) {
        purchaseState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved purchase", Purchase.class));
        paymentState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved payment", Payment.class));
    }

    @Override
    public void processElement1(Purchase value, KeyedCoProcessFunction<String, Purchase, Payment, Deal>.Context ctx,
            Collector<Deal> out) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void processElement2(Payment value, KeyedCoProcessFunction<String, Purchase, Payment, Deal>.Context ctx,
            Collector<Deal> out) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTimer(long t, OnTimerContext context, Collector<Deal> out) {
    }
}