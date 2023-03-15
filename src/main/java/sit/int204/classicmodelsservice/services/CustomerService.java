package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Id " + customerId + " DOES NOT EXIST !!!"));
    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public Page<Customer> getCustomerPages(Integer page, Integer size) {
        Pageable pageable =  PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }
}
