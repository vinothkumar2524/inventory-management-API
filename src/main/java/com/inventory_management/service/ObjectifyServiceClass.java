package com.inventory_management.service;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.annotation.Entity;
import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

public class ObjectifyServiceClass {
	
	public int getNumberOfDevices()
	{
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped",false).list().size();
	}
	public List<Device> getAllDevices()
	{
		return ObjectifyService.ofy().load().type(Device.class).list();
	}
	public Device getDevice(long id)
	{
		Device device = ObjectifyService.ofy().load().type(Device.class).id(id).now();
		if(device != null)
		{
			return device;
		}
		else
		{
			return null;
		}
		
	}
	public boolean addDevice(Device device)
	{
		Result result = ObjectifyService.ofy().load().type(Device.class).id(device.getId());
		if (result == null)
		{
			ObjectifyService.ofy().save().entities(device).now();
			return true;
		}
		else
			return false;	
		
	}
	public void deleteDevice(long id)
	{
		ObjectifyService.ofy().delete().type(Device.class).id(id).now();
	}
	public List<Employee> getAllEmployee()
	{
		return ObjectifyService.ofy().load().type(Employee.class).list();
	}
	public Employee getEmployee(long employeeId)
	{
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		if(employee != null)
		{
			return employee;
		}
		else
		{
			return null;
		}
	}
	public void addEmployee(Employee employee)
	{
		int numberOfEmployes = ObjectifyService.ofy().load().type(Employee.class).list().size();
		employee.setId(numberOfEmployes + 1);
		ObjectifyService.ofy().save().entities(employee).now();
	}
	public Employee mapDevice(long employeeId,long deviceId)
	{
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		System.out.println("came here 1");
		Device device = ObjectifyService.ofy().load().type(Device.class).id(deviceId).now();
		if( employee != null && device != null)
		{
			System.out.println("came here 2");
			if(device.getIsMaped() != true)
			{
				employee.addDevice(deviceId);
				System.out.println("came here 3");
				device.setEmployeeId(employeeId);
				device.setIsMaped(true);
				//employee.setDeviceCount();
				ObjectifyService.ofy().save().entities(employee).now();
				ObjectifyService.ofy().save().entities(device).now();
				return employee;
			}
			else
				System.out.println("came here 4");
			{
				return this.getEmployee(device.getEmployeeId());
			}
			
		}
		else
		{
			return null;
		}
		
	}
	public int getDeviceCount(long employeeId)
	{
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		return employee.getDeviceId().size();
	}
	public long removeMapedDevice(long employeeId, long deviceId)
	{
		Device device = ObjectifyService.ofy().load().type(Device.class).id(deviceId).now();
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		
		if(employee.getDeviceId().contains(deviceId))
		{
			employee.getDeviceId().remove(deviceId);
			device.setIsMaped(false);
			ObjectifyService.ofy().save().entities(device).now();
			ObjectifyService.ofy().save().entities(employee).now();
			return deviceId;
		}
		else
		{
			return 0;
		}
	}
	public int getLaptopCount()
	{
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped",false).filter("deviceType","laptop").list().size();
	}
	public int getHeadsetCount()
	{
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped",false).filter("deviceType","headset").list().size();
	}
	public int getMonitorCount()
	{
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped",false).filter("deviceType","monitor").list().size();
	}
}
