package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.infinispan.creson.Factory;
import org.infinispan.creson.Shared;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Hello implements RequestHandler<Map<String,String>, String>{

    private static Factory factory = Factory.get("52.49.189.207:11222"); // CRESON initialization

    Semaphore semaphore = factory.getInstanceOf(Semaphore.class,"semaphore",false, false, new Object[]{1}); // general declaration

    @Shared ArrayList<String> list = new ArrayList<>(); // @shared with empty constructor only

    @Shared AtomicInteger integer = new AtomicInteger(); // *careful* with final class/methods (see below)

    @Shared Lock lock;// does *not* work as this checks thread id

    public String handleRequest(Map<String,String> toadd, Context context) {
        try {
            for (String value : toadd.values()) {
                semaphore.acquire();
                list.add(value);
                semaphore.release();
            }
            System.out.println(integer.incrementAndGet()); // method is final so the call is local.
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(list.size());
    }

}
