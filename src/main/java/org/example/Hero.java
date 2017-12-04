package org.example;

import java.util.Stack;



public class Hero implements Comparable<Hero> {
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

  public int play() {
    int taille = graph.getGraphSize();
    boolean[] visited = new boolean[taille];
    Room room = graph.getRoom(graph.randomRoom(0, taille - 1));
    Stack<Room> stack = new Stack<Room>();
    stack.push(room);
    visited[room.id] = true;
    while (!stack.isEmpty()) {
      Room currentRoom = stack.pop();
      System.out.println(currentRoom.id + " " + currentRoom.adjList.size());
      this.score += currentRoom.loot();
      for (Room r : currentRoom.adjList) {
        if (!visited[r.id]) {
          visited[r.id] = true;
          stack.push(r);
        }
      }
    }
    return this.score;
  }

  public String toString() {
    return ("The player " + this.name + " has " + this.score + " treasure(s)");
  }

  @Override
  public int compareTo(Hero other) {
    if (this.score - other.score != 0)
      return -(this.score - other.score);
    else
      return this.name.compareTo(other.name);
  }

}
