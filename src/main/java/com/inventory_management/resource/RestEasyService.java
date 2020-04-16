package com.inventory_management.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;



@ApplicationPath("/restapi")
public class RestEasyService extends Application {

    private Set < Object > singletons = new HashSet < Object > ();

    public RestEasyService() {
        this.singletons.add(new DeviceResource());
        this.singletons.add(new EmployeeResource());
        System.out.println("application class invoked");
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        this.singletons.add(corsFilter);
        }

    @Override
    public Set < Object > getSingletons() {
        return singletons;
    }
}
