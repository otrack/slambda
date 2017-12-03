package org.example;

import java.util.Random;
import java.util.Stack;

public class Hero implements Runnable, Comparable<Hero> {
  Room room = new Room(0);
  int score;
  Graph graph;
  String name;

  Hero(Graph graph) {
    this.graph = graph;
    score = 0;
    name = "default";
  }

  Hero(String name, Graph graph) {
    this.graph = graph;
    score = 0;
    this.name = name;
  }

  private int play() {
    int taille = graph.getGraphSize();
    boolean[] visited = new boolean[taille];
    Room room = graph.getRoom(graph.randomRoom(0, taille - 1));
    Stack<Room> stack = new Stack<Room>();
    stack.push(room);
    visited[room.id] = true;
    while(!stack.isEmpty()) {
      Room currentRoom = stack.pop();
      this.score +=  currentRoom.loot();
      for(Room r : currentRoom.adjList) {
        if(!visited[r.id]) {
          visited[r.id] = true;
          stack.push(r);
        }
      }
    }
    return this.score;
  }

  public int go() {
    return room.loot();
  }

  public String toString() {
    return ("The player " + this.name + " has " + this.score + " treasure(s)");
  }

  @Override
  public void run() {
    play();
  }

  @Override
  public int compareTo(Hero other) {
    if(this.score - other.score != 0)
      return -(this.score - other.score) ;
    else
      return this.name.compareTo(other.name);
  }

}
