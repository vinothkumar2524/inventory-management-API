package com.inventory_management.client;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.inventory_management.model.Device;

public class RestApiClient {
	public static void main(String args[])
	{
//		Client client =ClientBuilder.newClient();
//		Response response =  client.target("http://localhost:8080/restapi/device/2").request().get();
//		System.out.println(response.readEntity(Device.class).getBrand());
		Set<Device> deviceList = new HashSet();
		System.out.println(deviceList.size());
	}
	
}
