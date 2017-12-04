package org.example;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Room implements Serializable{
	
	
  private static final long serialVersionUID = 4805204995317415754L;
  
  @Id
  int id;
  int treasure;
  
	LinkedList<Room>  adjList = new LinkedList<>(); ;

	
	public Room(int id) {
	  this.id = id;
		treasure = 1;	
	}

	public int loot(){
	    if (treasure == 1) {
	      treasure = 0;
	      return 1;
	    }
	    return 0;
	}
	
}
