package org.example;

public class Game {

  public static void main(String[] args) {
    Graph gr = new Graph(5);


    gr.addUndirectedPath(0, 1);
    gr.addUndirectedPath(0, 2);
    gr.addUndirectedPath(0, 3);
    
    Hero h = new  Hero("hamza", gr);
    h.play();
    System.out.println(h);
  }

}
