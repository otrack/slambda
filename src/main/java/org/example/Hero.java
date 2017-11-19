package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.Map;

import org.infinispan.creson.Shared;

public class Hero implements RequestHandler<String, String>{
	
	@Shared Room room = new Room();

	@Override
	public String handleRequest(String HeroName, Context arg1) {

		return HeroName+room.loot();
	}
}
