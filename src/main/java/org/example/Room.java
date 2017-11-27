package org.example;

import java.io.Serializable;

public class Room implements Serializable{
	
	int treasure;
	
	public Room()  {
		treasure = 1;
	}
	
	public String loot(){
		if (treasure == 1) {
			treasure = 0;
			return " you got the treasure";
		}
		return " the treasure is aleady taken";	
	}
	

}
