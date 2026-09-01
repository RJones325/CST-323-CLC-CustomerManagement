package com.cst323.clccustomermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cst323.clccustomermanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}