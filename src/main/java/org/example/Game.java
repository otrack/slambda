package org.example;

import java.util.TreeMap;
import java.util.TreeSet;

public class Game {

  public static void main(String[] args) {
    TreeSet<Hero> scoreBoard = new TreeSet<>();
    
    Graph gr = new Graph(5);
    gr.addUndirectedPath(0, 1);
    gr.addUndirectedPath(0, 2);
    gr.addUndirectedPath(0, 3);
    gr.addUndirectedPath(2, 4);
    gr.addUndirectedPath(1, 3);
    
    
    Hero hamza = new Hero("Hamza", gr);
    Thread hamzaThread = new Thread(hamza); 
    
    Hero arbia = new Hero("Arbia", gr);
    Thread arbiaThread = new Thread(arbia);
    
    Hero asma = new Hero("Asma", gr);
    Thread asmaThread = new Thread(asma);
    
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
    
    for(Hero h : scoreBoard)
      System.out.println(h);   
  }

}
