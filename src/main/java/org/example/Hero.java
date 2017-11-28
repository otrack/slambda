package org.example;

import java.util.Random;

public class Hero {
  Room room = new Room(0);
  int score;
  Graph graph ; 
  Hero(Graph graph) {
    this.graph = graph;
    score = 0;
  }
	private int randomRoom(int lowerBound, int upperBound) {
	  Random r = new Random();
    return r.nextInt(upperBound-lowerBound) + lowerBound;
	}
  public int play(Room r , boolean[] visited) {
    if(visited[r.id])
      return 0;
    visited[r.id] = true;
    int score = r.loot();
    for(Room neighbour : r.adjList) {
      if(!visited[neighbour.id]) {
        score += play(neighbour, visited);
      }
    }
    return score;
  }
	
	public void play() {
	  int  taille = graph.getGraphSize();
	  boolean[] visited = new boolean[taille];
	  Room room = graph.getRoom(randomRoom(0, taille-1));
	  this.score= play(room, visited);
	}
  
	public int go() {
		return room.loot();
	}
	
	
}
