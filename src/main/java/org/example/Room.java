package org.example;

import java.io.Serializable;
import java.util.LinkedList;

public class Room implements Serializable{
	
	int treasure;
	int id; 
	LinkedList<Room> adjList;
	
	public Room(int id) {
	  this.id = id;
		treasure = 1;
		adjList = new LinkedList<>();
	}
	
	
	public int loot(){
		if (treasure == 1) {
			treasure = 0;
			return 1;
		}
		return 0;	
	}
	

}
