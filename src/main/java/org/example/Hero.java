package org.example;


public class Hero {
	
	Room room = new Room();
	
	public String go() {
		return room.loot();
	}
}
