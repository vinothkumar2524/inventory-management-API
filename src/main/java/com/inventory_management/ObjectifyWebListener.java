package com.inventory_management;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;
import com.inventory_management.model.Device;
import com.inventory_management.model.Employee;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.init();
    // This is a good place to register your POJO entity classes.
    ObjectifyService.register(Device.class);
    ObjectifyService.register(Employee.class);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }
}