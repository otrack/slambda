package org.example;

import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class Game {
 
  
  public static void main(String[] args) {
    TreeSet<Hero> scoreBoard = new TreeSet<>();
    
    Graph gr = new Graph(10000);
    
    for(int i = 0 ; i < gr.getGraphSize() ; i++) {
      gr.addUndirectedPath(i, gr.randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, gr.randomRoom(0, gr.getGraphSize()-1));
      gr.addUndirectedPath(i, gr.randomRoom(0, gr.getGraphSize()-1));
    }
    
    
    Hero batman = new Hero("Batman", gr);
    Thread batmanThread = new Thread(batman); 
    
    Hero superman = new Hero("Superman", gr);
    Thread superThread = new Thread(superman);
    
    Hero catwoman = new Hero("Catwoman", gr);
    Thread catThread = new Thread(catwoman);
    
    try {
      batmanThread.start();
      superThread.start();
      catThread.start();
      
      batmanThread.join();
      superThread.join();
      catThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    scoreBoard.add(batman);
    scoreBoard.add(superman);
    scoreBoard.add(catwoman);
    
    for(Hero h : scoreBoard)
      System.out.println(h);   
  }

}
