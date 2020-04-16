package com.inventory_management.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.inventory_management.database.Database;
@XmlRootElement
@Entity
public class Employee {
	@Id long id;
	String name;
	Set<Long> deviceId;
	@Index int deviceCount;
	public Employee()
	{
		this.deviceId = new LinkedHashSet<Long>();
	}
	public Employee(long id,String name)
	{
		this.id = id;
		this.name = name;
		//this.deviceSet = new LinkedHashSet<Device>();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Long> getDeviceId() {
		return deviceId;
	}
	public boolean addDevice(long deviceId) {
		return this.deviceId.add(deviceId);
	}
	public int getDeviceCount()
	{
		return this.getDeviceId().size();
	}
	public void setDeviceCount()
	{
		this.deviceCount = this.getDeviceId().size();
	}
}
