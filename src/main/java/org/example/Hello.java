package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.danekja.java.util.function.serializable.SerializableBiFunction;
import org.infinispan.creson.Factory;
import org.infinispan.creson.Shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Hello implements RequestHandler<Map<String, String>, String> {

    private static Factory factory = Factory.get("creson.otrack.org:11222"); // Creson initialization

    @Shared ArrayList<String> list = new ArrayList<>(); // @Shared works with empty constructor only
    @Shared Map<Integer, Integer> map = new HashMap<>();
    @Shared TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public String handleRequest(Map<String, String> toadd, Context context) {
        Random random = new Random(System.currentTimeMillis());
        try {
            for (String value : toadd.values()) {
                list.add(value);
            }

            map.put(1, 0);
            map.put(2, 2);
            if (!map.containsKey(3)) {
                map.put(3,2);
            }
            System.out.println("map size = "+map.size());
            System.out.println("new value = "+map.computeIfPresent(
                    3,
                    (SerializableBiFunction<Integer, Integer, Integer>) (k, v) -> v*v));

                    // tree map
                    treeMap.put(random.nextInt(), random.nextInt());
            System.out.println("tree map size = "+treeMap.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(list.size());
    }

}
