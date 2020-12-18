package com.inventory_management.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

public class EmployeeDao {

	public List<Employee> getAllEmployee() {
		return ObjectifyService.ofy().load().type(Employee.class).list();
	}

	public Employee getEmployee(long employeeId) {
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		if (employee != null) {
			return employee;
		} else {
			return null;
		}
	}

	public void addEmployee(Employee employee) {
		int numberOfEmployes = ObjectifyService.ofy().load().type(Employee.class).list().size();
		employee.setId(numberOfEmployes + 1);
		ObjectifyService.ofy().save().entities(employee).now();
	}

	public Employee mapDevice(long employeeId, long deviceId) {
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		System.out.println("came here 1");
		Device device = ObjectifyService.ofy().load().type(Device.class).id(deviceId).now();
		if (employee != null && device != null) {
			System.out.println("came here 2");
			if (device.getIsMaped() != true) {
				employee.addDevice(deviceId);
				System.out.println("came here 3");
				device.setEmployeeId(employeeId);
				device.setIsMaped(true);
				// employee.setDeviceCount();
				ObjectifyService.ofy().save().entities(employee).now();
				ObjectifyService.ofy().save().entities(device).now();
				return employee;
			} else {
				return this.getEmployee(device.getEmployeeId());
			}

		} else {
			return null;
		}

	}

	public int getDeviceCount(long employeeId) {
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();
		return employee.getDeviceId().size();
	}

	public long removeMapedDevice(long employeeId, long deviceId) {
		Device device = ObjectifyService.ofy().load().type(Device.class).id(deviceId).now();
		Employee employee = ObjectifyService.ofy().load().type(Employee.class).id(employeeId).now();

		if (employee.getDeviceId().contains(deviceId)) {
			employee.getDeviceId().remove(deviceId);
			device.setIsMaped(false);
			ObjectifyService.ofy().save().entities(device).now();
			ObjectifyService.ofy().save().entities(employee).now();
			return deviceId;
		} else {
			return 0;
		}
	}

}
