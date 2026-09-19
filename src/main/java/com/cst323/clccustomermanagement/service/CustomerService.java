package com.cst323.clccustomermanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cst323.clccustomermanagement.model.Customer;
import com.cst323.clccustomermanagement.repository.CustomerRepository;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        logger.info("Entering getAllCustomers");
        List<Customer> customers = customerRepository.findAll();
        logger.info("Exiting getAllCustomers");
        return customers;
    }

    public Customer saveCustomer(Customer customer) {
        logger.info("Entering saveCustomer");
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Exiting saveCustomer");
        return savedCustomer;
    }

    public Customer getCustomerById(Long id) {
        logger.info("Entering getCustomerById");
        Customer customer = customerRepository.findById(id).orElse(null);
        logger.info("Exiting getCustomerById");
        return customer;
    }

    public void deleteCustomer(Long id) {
        logger.info("Entering deleteCustomer");
        customerRepository.deleteById(id);
        logger.info("Exiting deleteCustomer");
    }
}