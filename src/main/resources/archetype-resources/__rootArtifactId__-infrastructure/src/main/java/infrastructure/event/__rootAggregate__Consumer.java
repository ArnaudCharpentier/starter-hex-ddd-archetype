#set($symbol_dollar='$')
#set($symbol_opening_accolade='{')
#set($symbol_closing_accolade='}')
#set($kafka_topic = "${symbol_dollar}${symbol_opening_accolade}kafka.topic.topic_${rootAggregateLowerCase}${symbol_closing_accolade}")
package ${package}.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import ${package}.infrastructure.event.model.K${rootAggregate};
import ${package}.infrastructure.event.adapter.${rootAggregate}EventMapper;


@Component
public class ${rootAggregate}Consumer implements Consumer<Message<K${rootAggregate}>>{
    private static final Logger LOGGER = LoggerFactory.getLogger(${rootAggregate}Consumer.class);
    
    @Autowired
    ${rootAggregate}EventMapper ${rootAggregateLowerCase}Mapper; 
    
    @KafkaListener(topics = "${kafka_topic}", containerFactory = "${rootAggregateLowerCase}AvroKafkaListenerContainerFactory")
    public void consume(Message<K${rootAggregate}> messageKafka) {
        LOGGER.debug("consume ${rootAggregateLowerCase}: {}", messageKafka);

        /**
         * Here put your business logic
         * when you receive a message 
         * from kafka.topic.topic_${rootAggregateLowerCase} topic
         */
    }
    
}