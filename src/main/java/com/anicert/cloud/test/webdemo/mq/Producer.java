package com.anicert.cloud.test.webdemo.mq;

import com.anicert.cloud.test.webdemo.config.RocketMqConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Producer {

    @Autowired
    private RocketMqConfig rocketMqConfig;
    private DefaultMQProducer producer;

//    public Producer(){
//        producer = new DefaultMQProducer(rocketMqConfig.getProducerGroup());
//        producer.setVipChannelEnabled(false);
//        producer.setNamesrvAddr(rocketMqConfig.getNameServer());
//        start();
//    }


    public void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer(){
        if (this.producer == null) {
            producer = new DefaultMQProducer(rocketMqConfig.getProducerGroup());
            producer.setVipChannelEnabled(false);
            producer.setNamesrvAddr(rocketMqConfig.getNameServer());
            return producer;
        } else {
            return this.producer;
        }
    }

    public void shutdown(){
        this.producer.shutdown();
    }
}
