package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.infinispan.creson.Factory;
import org.infinispan.creson.Shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Hello implements RequestHandler<Map<String, String>, String> {

    private static Factory factory = Factory.get("54.77.4.130:11222"); // CRESON initialization

    Semaphore semaphore = factory.getInstanceOf(Semaphore.class, "s", false, true, new Object[]{1}); // general declaration for a shared object

    @Shared ArrayList<String> list = new ArrayList<>(); // @Shared works with empty constructor only
    @Shared Map<Integer, Integer> map = new HashMap<>(); // hash maps are directly backed by the Infinispan cache
    @Shared TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    @Shared(create = true) ArrayList<Integer> alist  = new ArrayList<>(); // force the re-creation of the list.

    // *Not working examples*
    @Shared AtomicInteger integer = new AtomicInteger(); // class/methods are final (see below)
    @Shared Lock lock;// use of the thread id

    public String handleRequest(Map<String, String> toadd, Context context) {
        Random random = new Random(System.currentTimeMillis());
        try {
            for (String value : toadd.values()) {
                list.add(value);
            }

            semaphore.acquire();
            semaphore.release();

            // hash map
            map.put(1, 0);
            map.put(2, 2);
            if (!map.containsKey(3)) {
                map.put(3,2);
            }
            System.out.println("map size = "+map.size());
            System.out.println("new value = "+map.computeIfPresent(3, (k,v) -> v*v));

            // tree map
            treeMap.put(random.nextInt(), random.nextInt());
            System.out.println("tree map size = "+treeMap.size());

            // create = true
            alist.add(random.nextInt());
            assert alist.size()==1;

            /*
             integer.incrementAndGet(); // method is final so the call is local.
             lock.lock();
             lock.unlock(); // raises an exception
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(list.size());
    }

}
