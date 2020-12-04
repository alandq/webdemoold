package com.anicert.cloud.test.webdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RocketMqConfig {

    @Value("${rocketmq.namesrv}")
    public String nameServer;

    @Value("${rocketmq.producer.group}")
    public String producerGroup;

    @Value("${rocketmq.message.topic}")
    public String messageTopic;

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }
}
