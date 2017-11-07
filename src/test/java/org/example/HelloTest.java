package org.example;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.infinispan.creson.Factory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloTest {

    private Hello caller;
    private Context context;

    @Before
    public void setUp() throws Exception {
        Factory.get("localhost:11222"); // Creson initialization
        caller = new Hello();
        context = new Context(){

            public String getAwsRequestId() {
                return null;
            }

            public String getLogGroupName() {
                return null;
            }

            public String getLogStreamName() {
                return null;
            }

            public String getFunctionName() {
                return null;
            }

            public String getFunctionVersion() {
                return null;
            }

            public String getInvokedFunctionArn() {
                return null;
            }

            public CognitoIdentity getIdentity() {
                return null;
            }

            public ClientContext getClientContext() {
                return null;
            }

            public int getRemainingTimeInMillis() {
                return 0;
            }

            public int getMemoryLimitInMB() {
                return 0;
            }

            public LambdaLogger getLogger() {
                return null;
            }
        };
    }

    @Test
    public void should_handle_request() {
        Map<String,String> map = new HashMap<>();
        map.put("a","1");
        System.out.println(caller.handleRequest(map, context));
    }

}