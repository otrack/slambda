package org.example;

public class Room {
	
	int treasure=1;
	
	public Room(){
		
	}
	
	public String loot(){
		if (treasure==1){
			treasure=0;
			return " you got the treasure";
		}
		return " the treasure is aleady taken";
		
	}
	

}
