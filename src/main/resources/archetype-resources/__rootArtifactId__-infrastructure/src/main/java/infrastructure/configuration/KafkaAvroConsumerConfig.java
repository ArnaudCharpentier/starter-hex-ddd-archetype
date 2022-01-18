#set($symbol_dollar='$')
#set($symbol_opening_accolade='{')
#set($symbol_closing_accolade='}')
#set($kafka_consumer_group = "${symbol_dollar}${symbol_opening_accolade}kafka.consumergroup.consumer_group_${rootAggregateLowerCase}${symbol_closing_accolade}")
package ${package}.infrastructure.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
@EnableKafka
public class KafkaAvroConsumerConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka_consumer_group}")
    private String ${rootAggregateLowerCase}ConsumerGroup;
    
    @Value(value = "${kafka.schemaRegistry.address}")
    private String schemaRegistryAddress;

    public Map<String, Object> consumerConfigs(String consumerGroup) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryAddress);
        return props;
    }
    
    public ConsumerFactory<String, Object> contratEnRetardAvroConsumerFactory(String groupId) {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(groupId));
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> ${rootAggregateLowerCase}AvroKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(contratEnRetardAvroConsumerFactory(${rootAggregateLowerCase}ConsumerGroup));
        return factory;
    }
}
