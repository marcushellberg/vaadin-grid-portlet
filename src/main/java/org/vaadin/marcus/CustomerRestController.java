package org.vaadin.marcus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vaadin.marcus.entity.Customer;
import org.vaadin.marcus.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/services/customers")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> findCustomers(
            @RequestParam(value = "index", required = false, defaultValue = "0") int index,
            @RequestParam(value = "count", required = false, defaultValue = "50") int count,
            @RequestParam(value = "sort", required = false, defaultValue = "firstName") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order) {

        System.out.println("***FINDING CUSTOMERS!");
        return customerService.findCustomers();
    }
}
