package org.example;

import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class TreasureTest {
  private Hero hamza;
  private Hero arbia;
  private Hero asma;

  private Thread hamzaThread;
  private Thread arbiaThread;
  private Thread asmaThread;

  private TreeSet<Hero> scoreBoard;
  
  private static int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }
  @Before
  public void setUp() throws Exception {
    scoreBoard = new TreeSet<>();

    Graph gr = new Graph(100);
    for(int i = 0 ; i < gr.getGraphSize() ; i++) {
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
    };

    hamza = new Hero("Hamza", gr);
    hamzaThread = new Thread(hamza);

    arbia = new Hero("Arbia", gr);
    arbiaThread = new Thread(arbia);

    asma = new Hero("Asma", gr);
    asmaThread = new Thread(asma);
  }

  @Test
  public void should_rank_heros() {
    try {
      hamzaThread.start();
      arbiaThread.start();
      asmaThread.start();

      hamzaThread.join();
      asmaThread.join();
      arbiaThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    scoreBoard.add(asma);
    scoreBoard.add(hamza);
    scoreBoard.add(arbia);

    for (Hero h : scoreBoard)
      System.out.println(h);
  }
}
