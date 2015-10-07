package org.vaadin.marcus.service;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.entity.Customer;
import org.vaadin.marcus.repository.CustomerRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerService {

    DataFactory datafactory = new DataFactory();

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    @PostConstruct
    private void initCustomers() {
        int numCustomers = 100;

        ArrayList<Customer> customers = new ArrayList<>(numCustomers);

        for (int i = 0; i < numCustomers; i++) {

            Customer customer = new Customer();
            customer.setFirstName(datafactory.getFirstName());
            customer.setLastName(datafactory.getLastName());
            customer.setAddress(datafactory.getAddress());
            customer.setCity(datafactory.getCity());
            customer.setZip(datafactory.getNumberText(5));
            customer.setCompany(datafactory.getBusinessName());

            customers.add(customer);
        }

        customerRepository.save(customers);
    }
}
