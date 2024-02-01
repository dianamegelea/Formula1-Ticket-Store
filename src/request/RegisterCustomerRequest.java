package request;

import domain.Customer;
import exceptions.ClientExistsException;

import java.util.List;

public class RegisterCustomerRequest implements Runnable {
    private Customer customer;
    private List<Customer> customers;

    public RegisterCustomerRequest(List<Customer> customers, Customer customer) {
        this.customer = customer;
        this.customers = customers;
    }

    @Override
    public void run() {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }
}
