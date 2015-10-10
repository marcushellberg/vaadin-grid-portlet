package org.vaadin.marcus;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.entity.Customer;
import org.vaadin.marcus.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Random;

@Component
public class TestDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  CustomerRepository customerRepository;
  DataFactory datafactory = new DataFactory();

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    if(customerRepository.count() == 0) {
      int numCustomers = 10000;

      ArrayList<Customer> customers = new ArrayList<>(numCustomers);
      Random random = new Random();
        Customer.OrderProgress[] statuses = Customer.OrderProgress.values();
      for (int i = 0; i < numCustomers; i++) {

        Customer customer = new Customer();
        customer.setFirstName(datafactory.getFirstName());
        customer.setLastName(datafactory.getLastName());
        customer.setAddress(datafactory.getAddress());
        customer.setCity(datafactory.getCity());
        customer.setZip(datafactory.getNumberText(5));
        customer.setCompany(datafactory.getBusinessName());
        customer.setOrderProgress(statuses[random.nextInt(statuses.length)]);

        customers.add(customer);
      }
      customerRepository.save(customers);

      System.out.println("Generated " + numCustomers + " test customers");
    }
  }
}