package org.vaadin.marcus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.marcus.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
