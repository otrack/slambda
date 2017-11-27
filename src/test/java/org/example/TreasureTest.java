package org.example;

import org.infinispan.creson.Factory;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class TreasureTest {
	private Hero hero;
    
    @Before
    public void setUp() throws Exception {
        Factory.get("localhost:11222"); // Creson initialization
        hero = new Hero();
    }
    @Test
    public void should_handle_request() {
    	
        System.out.println(hero.go());
    }

    
}
