package com.inventory_management.model;

import javax.xml.bind.annotation.XmlRootElement;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@XmlRootElement
@Entity
public class Device {
	@Id long id;
	@Index private String deviceType;
	@Index private String brand;
	private long  employeeId;
	@Index private boolean isMaped;
	public Device() {

	}
	public Device(long id,String deviceType,String brand)
	{
		this.id = id;
		this.deviceType = deviceType;
		this.brand = brand;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getBrand() {
		return brand;
	}

	public boolean getIsMaped() {
		return isMaped;
	}
	public void setIsMaped(boolean isMaped) {
		this.isMaped = isMaped;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

}

