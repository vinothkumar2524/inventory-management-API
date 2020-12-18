package com.inventory_management.resource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.inventory_management.dao.DeviceDao;
import com.inventory_management.model.Device;
import com.inventory_management.model.ErrorMessage;
import com.inventory_management.service.DeviceService;
import com.inventory_management.service.ObjectifyServiceClass;

@Path("/device")
public class DeviceResource {
	DeviceService deviceService = new DeviceService();
	DeviceDao deviceDao = new DeviceDao();
	

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumberOfDevices() {
		return Response.status(Status.OK)
				.entity(deviceDao.getNumberOfDevices()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDevices() {

		return Response.status(Status.OK)
				.entity(deviceDao.getAllDevices()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDevice(@PathParam("id") long id) {
		Device device = deviceDao.getDevice(id);
		if (device != null) {
			return Response.status(Status.OK).entity(device).build();

		} else {
			ErrorMessage error = new ErrorMessage("Device with the mentioned device id was not found", 404);
			return Response.status(Status.NO_CONTENT).entity(error).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDevice(Device device) {
		if (deviceDao.addDevice(device)) {
			return Response.status(Status.OK).build();
		} else {
			ErrorMessage error = new ErrorMessage("A device with same id already exist", 409);
			return Response.status(Status.CONFLICT).entity(error).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDevice(@PathParam("id") long id) {
		if(deviceDao.deleteDevice(id))
			return Response.status(Status.OK).build();
		else {
			ErrorMessage error = new ErrorMessage("No such device found", 404);
			return Response.status(Status.NOT_FOUND).entity(error).build();
		}
	}

	@GET
	@Path("/laptopCount")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLaptopCount() {
		int count = deviceDao.getLaptopCount();
		return Response.status(Status.OK).entity(count).build();
	}

	@GET
	@Path("/headsetCount")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHeadsetCount() {
		int count = deviceDao.getHeadsetCount();
		return Response.status(Status.OK).entity(count).build();
	}

	@GET
	@Path("/monitorCount")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMonitorCount() {
		int count = deviceDao.getMonitorCount();
		return Response.status(Status.OK).entity(count).build();
	}

}