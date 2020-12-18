package com.inventory_management.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.googlecode.objectify.ObjectifyService;
import com.inventory_management.dao.EmployeeDao;
import com.inventory_management.model.Employee;
import com.inventory_management.service.EmployeeService;
import com.inventory_management.service.ObjectifyServiceClass;

@Path("/employee")
public class EmployeeResource {

	EmployeeService employeeService = new EmployeeService();
	EmployeeDao employeeDao = new EmployeeDao();
	ObjectifyServiceClass objectifyService = new ObjectifyServiceClass();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
				.entity(employeeDao.getAllEmployee()).build();
	}

	@GET
	@Path("/emp/{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("employeeId") long employeeId) {
		System.out.println("got here");
		Employee employee = employeeDao.getEmployee(employeeId);
		if (employee != null) {
			return Response.status(Status.OK)
					.entity(employee).build();
		} else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
		return Response.status(Status.OK).build();
	}

	@POST
	@Path("/{employeeId}/{deviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response mapDevice(@PathParam("employeeId") long employeeId, @PathParam("deviceId") long deviceId) {
		Employee employee = employeeDao.mapDevice(employeeId, deviceId);
		System.out.println("came here 0");
		if (employee == null) {
			return Response.status(Status.NO_CONTENT).build();
		} else if (employee.getId() == employeeId) {
			return Response.status(Status.OK).entity(employee)
					.build();
		} else {
			return Response.status(Status.CONFLICT).entity(employee)
					.build();
		}

	}

	@GET
	@Path("/deviceCount/{employeeId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDeviceCount(@PathParam("employeeId") long employeeId) {
		int deviceCount = employeeDao.getDeviceCount(employeeId);
		return Response.status(Status.OK).entity(deviceCount).build();
	}

	@POST
	@Path("/removeMapping/{employeeId}/{deviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMappedDevice(@PathParam("employeeId") long employeeId, @PathParam("deviceId") long deviceId) {
		employeeDao.removeMapedDevice(employeeId, deviceId);
		return Response.status(Status.OK).build();
	}
}
