package org.example;


public class Hero {
	
	Room room = new Room(0);
	
	public int go() {
		return room.loot();
	}
}
