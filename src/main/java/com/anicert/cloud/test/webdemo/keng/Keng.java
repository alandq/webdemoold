package com.anicert.cloud.test.webdemo.keng;

import java.util.HashMap;
import java.util.Map;

public class Keng {

    private String string;

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        map.put("key1","value1");
        map.put("key2","value2");

        for (String key:map.keySet()){
            System.out.println(map.get(key));
        }
    }

    private String testNull(){
        if (string.equals("test")){
            return "not null";
        }else{
            return "null";
        }
    }
}
