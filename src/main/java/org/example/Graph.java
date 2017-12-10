package org.example;


import org.infinispan.creson.Shared;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Graph implements Serializable {


  private static final long serialVersionUID = -1722773175340282633L;
  
  private int numberNodes;
  
  @Shared
  ArrayList<Room> nodes = new ArrayList<>();


  public Graph() {};


  public Graph(int n) {
    this.numberNodes = n;
    for (int i = 0; i < n; i++) {
      nodes.add(new Room(i));
    }
  }

  public int getGraphSize() {
    return numberNodes;
  }

  public Room addRoom(int roomId) {
    nodes.add(roomId, new Room(roomId));
    return nodes.get(roomId);
  }

  public Room getRoom(int roomId) {
    return nodes.get(roomId);
  }

  public void addDirectedPath(int begId, int endId) {

    Room beg = getRoom(begId);
    Room end = getRoom(endId);
    if (beg == null) {
      beg = addRoom(begId);
    }
    if (end == null) {
      end = addRoom(endId);
    }
    beg.addRoom(end);

  }

  public void addUndirectedPath(int roomA, int roomB) {
    addDirectedPath(roomA, roomB);
    addDirectedPath(roomB, roomA);
  }

  public int randomRoom(int lowerBound, int upperBound) {
    Random r = new Random();
    return r.nextInt(upperBound - lowerBound) + lowerBound;
  }


  @Override
  public String toString(){
    String ret="";
    for(int i=0; i<this.nodes.size(); i++) {
      ret+=this.nodes.get(i).toString();
      if (i<this.nodes.size()-1) ret+=",";
    }
    return ret;

  public static void main(String[] args) {


  }

}
