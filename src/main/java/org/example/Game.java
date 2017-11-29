package org.example;

import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class Game {
  
  private static int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }
  
  public static void main(String[] args) {
    TreeSet<Hero> scoreBoard = new TreeSet<>();
    
    Graph gr = new Graph(100);
    
    for(int i = 0 ; i < gr.getGraphSize() ; i++) {
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, randomRoom(0, gr.getGraphSize()-1));
    }
    
    
    
    
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
