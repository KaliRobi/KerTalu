package com.kertalu.kertalu.messaging;

import com.kertalu.kertalu.subscription.Subscription;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SubscriptionKafkaProducer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC_NAME = "subscription-topic";
    private KafkaProducer<String, String> producer;

    public SubscriptionKafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(properties);
    }

    public void sendSubscriptionEvent(Subscription subscription) {

        String subscriptionJson = String.format("{\"id\":\"%s\", \"tier\":\"%s\", \"client\":\"%s\"}",
                subscription.getId(),
                subscription.getTier(),
                subscription.getClient());


        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, subscription.getClient().getName(), subscriptionJson);

        try {
            // Asynchronously send the message
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println("Message sent successfully to topic: " + metadata.topic());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeProducer() {
        if (producer != null) {
            producer.close();
        }
    }
}