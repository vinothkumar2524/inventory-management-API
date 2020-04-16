package com.inventory_management.service;

import java.util.HashMap;
import java.util.Map;

import com.inventory_management.database.Database;
import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

public class EmployeeService {
	
	Map<Long,Employee> employeesMap = Database.getEmployees();
	public EmployeeService()
	{
		employeesMap.put(1L, new Employee(1,"jagan"));
		employeesMap.put(2L, new Employee(2,"kapil"));
	}
	
	public Map<Long,Employee> getAllEmployee()
	{
		return employeesMap;
	}
	public Employee getEmployee(long id)
	{
		return employeesMap.get(id);
	}
	public Employee addEmployee(Employee employee)
	{
		employee.setId(employeesMap.size() + 1);
		return employeesMap.put(employee.getId(), employee);
	}
	public Employee deleteEmployee(long id)
	{
		return employeesMap.remove(id);
	}
//	public boolean mapDevice(long employeeId,long deviceId)
//	{
//		DeviceService deviceService = new DeviceService();
//		Device device = deviceService.getDevice(deviceId);
//		//return employeesMap.get(employeeId).addDevice(device);
//		
//	}
}
