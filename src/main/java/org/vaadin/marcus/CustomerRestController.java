package org.vaadin.marcus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.marcus.entity.Customer;
import org.vaadin.marcus.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/services/customers")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;


    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> findCustomers(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "50") int size,
            @RequestParam(value = "sort", required = false, defaultValue = "firstName") String sortProperty,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order) {

        System.out.println(String.format("Finding customers [page: %d, size: %d, sort: %s, order: %s", page, size, sortProperty, order));

        Sort.Direction sortDirection = order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        // Our response will contain both a size and the result set.
        HashMap<String, Object> response = new HashMap<>();

        // Grid makes a call for 0 size first, need to handle that separately
        if (page == 0 && size == 0) {
            response.put("total", customerRepository.count());
            response.put("results", new ArrayList());
        } else {
            PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortProperty);
            Page<Customer> customers = customerRepository.findAll(pageRequest);
            response.put("total", customers.getTotalElements());
            response.put("results", customers.getContent());
        }

        return response;
    }
}
