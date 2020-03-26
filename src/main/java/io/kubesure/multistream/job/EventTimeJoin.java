package io.kubesure.multistream.job;

import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Customer;
import io.kubesure.multistream.datatypes.JoinedEvents;
import io.kubesure.multistream.datatypes.Trade;

public class EventTimeJoin extends KeyedCoProcessFunction<Long,Trade,Customer,JoinedEvents> {

    private static final long serialVersionUID = 1L;
    private MapState<Long, Customer> customerMap = null;
    private MapState<Long, Trade> tradeMap = null;
    //private MapState<Long, Payment> paymentMap = null;

    private static final Logger log = LoggerFactory.getLogger(EventTimeJoin.class);

    public EventTimeJoin(){}

    @Override
    public void open(Configuration config)  {
        MapStateDescriptor cDescriptor = new MapStateDescriptor<Long,Customer> (
            "customerBuffer",
            TypeInformation.of(Long.class),
            TypeInformation.of(Customer.class)
        );

        customerMap = getRuntimeContext().getMapState(cDescriptor);

        MapStateDescriptor tDescriptor = new MapStateDescriptor<Long,Trade> (
            "customerBuffer",
            TypeInformation.of(Long.class),
            TypeInformation.of(Trade.class)
        );

        tradeMap = getRuntimeContext().getMapState(tDescriptor);

        /*MapStateDescriptor pDescriptor = new MapStateDescriptor<Long,Payment> (
            "customerBuffer",
            TypeInformation.of(Long.class),
            TypeInformation.of(Payment.class)
        );

        paymentMap = getRuntimeContext().getMapState(pDescriptor);*/
    }

    @Override
    public void processElement1(Trade trade, Context context, Collector<JoinedEvents> out) throws Exception{
         log.info(trade.toString());       
    }

    @Override
    public void processElement2(Customer customer, Context context, Collector<JoinedEvents> out) throws Exception{
        log.info(customer.toString());
    }

   /* @Override
    public void processElement3(Payment payment, Context context, Collector<JoinedEvents> out) throws Exception{
        
    }*/

}