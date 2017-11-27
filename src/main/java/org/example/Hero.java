package org.example;


import org.infinispan.creson.Shared;

public class Hero {
	
	@Shared Room room = new Room();
	
	public String go() {
		return room.loot();
	}
}
