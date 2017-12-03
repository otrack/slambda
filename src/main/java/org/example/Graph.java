package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graph {
  private int numberNodes ; 
  Room[] nodes;
  
  public Graph(int n) {
    this.numberNodes = n;
    nodes = new Room[n];
    for(int i = 0; i < n; i++) {
      nodes[i] = new Room(i);
    };
  }
  
  public int getGraphSize() {
    return numberNodes;
  }
  
  public Room addRoom(int roomId) {
    nodes[roomId] = new Room(roomId);
    return nodes[roomId];
  }
  
  public Room getRoom(int roomId) {
    return nodes[roomId];
  }
  
  public void addDirectedPath(int begId, int endId) {
    Room beg ; 
    Room end; 
    if((beg = getRoom(begId)) == null) {
      beg = addRoom(begId);
    }
    if((end = getRoom(endId)) ==null) {
      end = addRoom(endId);
    }
    beg.adjList.add(end);  
  }
  
  public void addUndirectedPath(int roomA , int roomB) {
    addDirectedPath(roomA, roomB);
    addDirectedPath(roomB, roomA);
  }
  
  public int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }
  public static void main(String[] args) {
    
  }

}
