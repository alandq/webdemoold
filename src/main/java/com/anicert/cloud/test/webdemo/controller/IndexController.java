package com.anicert.cloud.test.webdemo.controller;

import com.anicert.cloud.test.webdemo.config.RocketMqConfig;
import com.anicert.cloud.test.webdemo.mq.Producer;
import com.anicert.cloud.test.webdemo.vo.TestVo;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RocketMqConfig rocketMqConfig;

    @Autowired
    private Producer producer;

    @GetMapping("/index")
    public String index(ModelMap map){

        TestVo t = new TestVo();
        t.setTest1("原始字符串1");
        t.setTest2("原始字符串2");
        t.setTest3("原始字符串3");

        map.addAttribute("test",t);
        return "thymeleaf/index";
    }

    @GetMapping("/get1")
    public String get1(ModelMap map){

        String url = "http://appdemo1/getstring";

        ResponseEntity<String> respnose = restTemplate.getForEntity(url,String.class);
        respnose.getBody();

        TestVo t = new TestVo();
//        t.setTest1("原始字符串");
        // 从appdemoe1的getstring接口获取字符串
        t.setTest1(respnose.getBody());

        map.addAttribute("test",t);
        return "thymeleaf/index::div1";
    }

    @GetMapping("/get2")
    public String get2(ModelMap map){

        String url = "http://appdemo1/getdbstring";

        ResponseEntity<String> respnose = restTemplate.getForEntity(url,String.class);
        respnose.getBody();

        TestVo t = new TestVo();
//        t.setTest1("原始字符串");
        // 从appdemoe1的getstring接口获取字符串
        t.setTest2(respnose.getBody());

        map.addAttribute("test",t);
        return "thymeleaf/index::div2";
    }

    @GetMapping("/get3")
    public String get3(@RequestParam String message, ModelMap map){

        Message mqMessage = new Message(rocketMqConfig.messageTopic, "testtag", message.getBytes());

        try {
            DefaultMQProducer dp = producer.getProducer();
            producer.start();
            producer.getProducer().send(mqMessage);
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "thymeleaf/index::div3";
    }
}
