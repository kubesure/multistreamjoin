package io.kubesure.multistream.sink;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.walkthrough.common.entity.Alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Sink implements SinkFunction<Alert> {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(Sink.class);

	@Override
	public void invoke(Alert value, Context context) {
		log.info(value.toString());
	}
}