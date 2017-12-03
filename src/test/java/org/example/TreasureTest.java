package org.example;

import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class TreasureTest {
  int number = 10;
  Graph gr;
  private TreeSet<Hero> scoreBoard;
  Thread[] thread = new Thread[number];
  Hero[] hero = new Hero[number];
  
  private static int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }
  @Before
  public void setUp() throws Exception {
    scoreBoard = new TreeSet<>();

    gr = new Graph(100000);
    for(int i = 0 ; i < gr.getGraphSize() ; i++) {
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
    };
  }

  @Test
  public void should_rank_heros() {

    try {
      
      for(int i = 1 ; i <= number ; i++) {
        hero[i-1] = new Hero("Hero" + i, gr);
        thread[i-1] = new Thread(hero[i-1]);
      }
      
      for(int i = 0; i < number ; i++) {
        thread[i].start();
      }
      
      for(int i = 0 ; i < number ; i++) {
        thread[i].join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for(int i = 0 ; i < number  ; i++) {
      scoreBoard.add(hero[i]);
    }

    for (Hero h : scoreBoard)
      System.out.println(h);
  }
}
