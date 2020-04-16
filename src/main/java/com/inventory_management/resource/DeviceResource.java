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

import com.inventory_management.model.Device;
import com.inventory_management.service.DeviceService;
import com.inventory_management.service.ObjectifyServiceClass;



@Path("/device")
public class DeviceResource {
	DeviceService deviceService = new DeviceService();
	ObjectifyServiceClass objectifyService = new ObjectifyServiceClass();
	
	@GET
	@Path("/getTotalDevices")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfDevices()
	{
		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*").entity(objectifyService.getNumberOfDevices()).build();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevices() {
    	
        return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*").entity(objectifyService.getAllDevices()).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevice(@PathParam("id") long id)
    {
    	Device device =objectifyService.getDevice(id);
    	if(device != null)
    	{
    		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*").entity(device).build();
    	}
    	else
    	{
    		return Response.status(Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
    	}
    	
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDevice(Device device)
    {
    	if(objectifyService.addDevice(device))
    	{
    		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
        			.header("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, HEAD, PUT, POST")
    				.header("Access-Control-Allow-Credentials", "true")
        			.build();
    	}
    	else
    	{
    		return Response.status(Status.CONFLICT).build();
    	}
    	
    	
    	
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDevice(@PathParam("id")long id)
    {
    	objectifyService.deleteDevice(id);
    	return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, HEAD, PUT, POST")
				.header("Access-Control-Allow-Credentials", "true")
				.build();
    }
    
    @GET
    @Path("/laptopCount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLaptopCount()
    {
    	int count = objectifyService.getLaptopCount();
    	return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, HEAD, PUT, POST")
				.header("Access-Control-Allow-Credentials", "true").entity(count)
				.build();
    }
    
    @GET
    @Path("/headsetCount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHeadsetCount()
    {
    	int count = objectifyService.getHeadsetCount();
    	return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, HEAD, PUT, POST")
				.header("Access-Control-Allow-Credentials", "true").entity(count)
				.build();
    }
    
    @GET
    @Path("/monitorCount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMonitorCount()
    {
    	int count = objectifyService.getMonitorCount();
    	return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods","GET, OPTIONS, DELETE, HEAD, PUT, POST")
				.header("Access-Control-Allow-Credentials", "true").entity(count)
				.build();
    }
    
}