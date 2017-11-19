package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import org.infinispan.creson.Shared;

public class Hero implements RequestHandler<Integer, String>{
	
	@Shared Room room = new Room();
	
	@Override
	public String handleRequest(Integer i, Context arg1) {
		return room.loot();
	}
}
