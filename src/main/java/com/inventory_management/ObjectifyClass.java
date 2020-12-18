package com.inventory_management;

import com.googlecode.objectify.ObjectifyService;
import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

public class ObjectifyClass {
	
	public static void store()
	{
		 ObjectifyService.ofy().save().entities(new Employee(1L,"jagandaw")).now();
		 ObjectifyService.ofy().save().entities(new Employee(2L,"kapildaw")).now();
		 ObjectifyService.ofy().save().entities(new Device(3L,"Laptop","Dell")).now();
		 

	}
}
