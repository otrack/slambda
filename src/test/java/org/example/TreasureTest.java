package org.example;

import java.util.Random;
import java.util.TreeSet;

import org.infinispan.creson.Factory;
import org.junit.Before;
import org.junit.Test;

public class TreasureTest {
  Graph gr;
  Hero superMan;
  private static int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }
  @Before
  public void setUp() throws Exception {
    Factory.get("localhost:11222");
    
    gr = new Graph(100000);
    for(int i = 0 ; i < gr.getGraphSize() ; i++) {
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
    };
    
   superMan = new Hero("SuperMan", gr);
  }

  @Test
  public void should_rank_heros() {
    superMan.play();
     System.out.println(superMan.score);
  }
}
