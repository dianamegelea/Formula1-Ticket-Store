package request;

import domain.Customer;

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
        customers.add(customer);
    }
}
