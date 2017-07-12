package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.infinispan.creson.Factory;
import org.infinispan.creson.StaticEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hello implements RequestHandler<Map<String,String>, String>{

    @StaticEntity
    public static List<String> list;

    public String handleRequest(Map<String,String> toadd, Context context) {
        // Initialize CRESON
        Factory.get("52.49.189.207:11222");
        list =  new ArrayList<>();

        for(String value: toadd.values()) {
            list.add(value);
        }
        return Integer.toString(list.size());
    }

}
