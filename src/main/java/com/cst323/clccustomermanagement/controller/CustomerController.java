package com.cst323.clccustomermanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cst323.clccustomermanagement.model.Customer;
import com.cst323.clccustomermanagement.service.CustomerService;

@Controller
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home() {
        logger.info("Entering home");
        logger.info("Exiting home");
        return "index";
    }

    @GetMapping("/customers")
    public String viewCustomers(Model model) {
        logger.info("Entering viewCustomers");
        model.addAttribute("customers", customerService.getAllCustomers());
        logger.info("Exiting viewCustomers");
        return "customers";
    }

    @GetMapping("/customers/new")
    public String showCustomerForm(Model model) {
        logger.info("Entering showCustomerForm");
        model.addAttribute("customer", new Customer());
        logger.info("Exiting showCustomerForm");
        return "customer-form";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(Customer customer) {
        logger.info("Entering saveCustomer");
        customerService.saveCustomer(customer);
        logger.info("Exiting saveCustomer");
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        logger.info("Entering editCustomer");
        model.addAttribute("customer", customerService.getCustomerById(id));
        logger.info("Exiting editCustomer");
        return "customer-form";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        logger.info("Entering deleteCustomer");
        customerService.deleteCustomer(id);
        logger.info("Exiting deleteCustomer");
        return "redirect:/customers";
    }
}