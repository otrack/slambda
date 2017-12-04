package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.infinispan.creson.Shared;

public class Graph implements Serializable{
  private int numberNodes ; 
  @Shared ArrayList<Room>  nodes = new ArrayList<>();
  
  public Graph() {};
  public Graph(int n) {
    this.numberNodes = n;
    for(int i = 0; i < n; i++) {
      nodes.add(new Room(i));
    };
  }
  
  public int getGraphSize() {
    return numberNodes;
  }
  
  public Room addRoom(int roomId) {
    nodes.add(roomId, new Room(roomId));;
    return nodes.get(roomId);
  }
  
  public Room getRoom(int roomId) {
    return nodes.get(roomId);
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
