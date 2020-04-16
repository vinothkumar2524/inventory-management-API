package com.inventory_management.database;

import java.util.HashMap;
import java.util.Map;

import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

public class Database {
	
	private static Map<Long,Device> devicesDatabase = new HashMap<Long,Device>();
	private static Map<Long,Employee> employeeDatabase = new HashMap<Long,Employee>();
	public static Map<Long,Device> getDevices()
	{
		return devicesDatabase;
	}
	public static Map<Long,Employee> getEmployees()
	{
		return employeeDatabase;
	}
}
