package chinh.nguyen.service;

import chinh.nguyen.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    void delete(Long id);
    Customer findById(Long id);
}
