package com.numberone.web.util;
import com.numberone.kafka.domain.Kafkamessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TestTopicReceiver{
    private static final Logger log = LoggerFactory.getLogger(TestTopicReceiver.class);
    public static List<Kafkamessage> ms=new ArrayList<>();
    @KafkaListener(topics = {"BootstrapTopic"},groupId = "test-consumer-group")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("消费信息");
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("-xiaofeng1-- test1：" + record+"，record:" + record+",message:" + message+",---");
            Kafkamessage map=new Kafkamessage();
            map.setMessage(message.toString());
            this.ms.add(map);
        }
    }
    public List<Kafkamessage> getMessage(){
        return  ms;
    }
//    @KafkaListener(topics = {"BootstrapTopic"},groupId = "test-consumer-group")
//    public void listen(List<ConsumerRecord> records) {
//        System.out.println("消费信息");
//        for (ConsumerRecord record : records) {
//            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//
//            if (kafkaMessage.isPresent()) {
//                Object message = kafkaMessage.get();
//                log.info("-xiaofeng1-- test1：" + record+"，record:" + record+",message:" + message+",---");
//            }
//        }
//    }

}