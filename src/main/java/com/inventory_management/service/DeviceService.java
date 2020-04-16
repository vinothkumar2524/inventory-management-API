package com.inventory_management.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory_management.database.Database;
import com.inventory_management.model.Device;

public class DeviceService {
	public Map<Long,Device> devicesMap = Database.getDevices();
	public DeviceService()
	{
		devicesMap.put(1L, new Device(1,"laptop","mac"));
		devicesMap.put(2L, new Device(2,"headset","sony"));
	}
	public Map<Long,Device> getAllDevices()
	{
		return devicesMap;
	}
	public Device getDevice(long id)
	{
		return devicesMap.get(id);
	}
	public Device addDevice(Device device)
	{
		device.setId(devicesMap.size()+1);
		return devicesMap.put(device.getId(), device);
	}
	public Device removeDevice(long id)
	{
		return devicesMap.remove(id);
	}
}
