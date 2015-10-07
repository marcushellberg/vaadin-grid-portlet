package org.vaadin.marcus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.marcus.entity.Customer;
import org.vaadin.marcus.service.CustomerService;

import java.util.List;

@RestController
public class CustomerRestAPI {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public List<Customer> findCustomers() {
        return customerService.findCustomers();
    }
}
