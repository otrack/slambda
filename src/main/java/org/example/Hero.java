package org.example;

import java.util.Random;

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

  private int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }

  private int play(Room r, boolean[] visited) {
    if (visited[r.id])
      return 0;
    visited[r.id] = true;
    int score = r.loot();
    for (Room neighbour : r.adjList) {
      if (!visited[neighbour.id]) {
        score += play(neighbour, visited);
      }
    }
    return score;
  }

  private void play() {
    int taille = graph.getGraphSize();
    boolean[] visited = new boolean[taille];
    Room room = graph.getRoom(randomRoom(0, taille - 1));
    this.score = play(room, visited);
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
