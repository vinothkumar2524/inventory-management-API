package com.inventory_management.dao;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;
import com.inventory_management.model.Device;
import com.inventory_management.service.ObjectifyServiceClass;
import com.google.gson.Gson;

public class DeviceDao {
	Logger logger = Logger.getLogger(ObjectifyServiceClass.class.getName());
	public int getNumberOfDevices() {
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped", false).list().size();
	}
	
	public String getAllDevices() {
		List devicesArrayList = new ArrayList();
		devicesArrayList = ObjectifyService.ofy().load().type(Device.class).list();
		Gson json = new Gson();
		return json.toJson(devicesArrayList);
	}

	public Device getDevice(long id) {
		Device device = ObjectifyService.ofy().load().type(Device.class).id(id).now();
		if (device != null) {
			return device;
		} else {
			return null;
		}
	}
	
	public boolean addDevice(Device device) {
		Device alreadyAvailableDevice = ObjectifyService.ofy().load().type(Device.class).id(device.getId()).now();
		if (alreadyAvailableDevice != null) {
			return false;
		} else {
			ObjectifyService.ofy().save().entities(device).now();
			return true;
		}
	}

	public boolean deleteDevice(long id) {
		Device device = ObjectifyService.ofy().load().type(Device.class).id(id).now();
		if(device != null) {
			if(device.getIsMaped()) {
				EmployeeDao employeeDao = new EmployeeDao();
				employeeDao.removeMapedDevice(device.getEmployeeId(), id);
			}
			ObjectifyService.ofy().delete().type(Device.class).id(id).now();
			System.out.println("_______________________________________________");
			return true;
		}
		return false;
	}

	public int getLaptopCount() {
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped", false).filter("deviceType", "laptop")
				.list().size();
	}

	public int getHeadsetCount() {
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped", false).filter("deviceType", "headset")
				.list().size();
	}

	public int getMonitorCount() {
		return ObjectifyService.ofy().load().type(Device.class).filter("isMaped", false).filter("deviceType", "monitor")
				.list().size();
	}
}
