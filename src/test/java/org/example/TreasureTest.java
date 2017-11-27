package org.example;

import org.junit.Before;
import org.junit.Test;



public class TreasureTest {
	private Hero hero;
    
    @Before
    public void setUp() throws Exception {
       // Factory.get("localhost:11222"); // Creson initialization
        hero = new Hero();
    }
    @Test
    public void should_fetch_treasure() {
    	
        System.out.println(hero.go());
    }

    
}
