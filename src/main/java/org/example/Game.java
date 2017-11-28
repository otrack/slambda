package org.example;

public class Game {

  public static void main(String[] args) {
    Graph gr = new Graph(4);
    gr.addRoom(0);
    gr.addRoom(1);
    gr.addRoom(2);
    gr.addRoom(3);

    gr.addUndirectedPath(0, 1);
    gr.addUndirectedPath(0, 2);
    //gr.addUndirectedPath(0, 3);
    
    Hero h = new  Hero(gr);
    h.play();
    System.out.println(h.score);
  }

}
