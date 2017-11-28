package org.example;

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

  @Before
  public void setUp() throws Exception {
    scoreBoard = new TreeSet<>();

    Graph gr = new Graph(10);
    gr.addUndirectedPath(0, 1);
    gr.addUndirectedPath(0, 2);
    gr.addUndirectedPath(0, 3);
    gr.addUndirectedPath(2, 4);
    gr.addUndirectedPath(1, 3);
    gr.addUndirectedPath(4, 6);
    gr.addUndirectedPath(7, 4);
    gr.addUndirectedPath(5, 7);
    gr.addUndirectedPath(8, 3);
    gr.addUndirectedPath(1, 9);

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
