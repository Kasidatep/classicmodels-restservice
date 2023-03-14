package sit.int204.classicmodelsservice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.dtos.PageDto;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDto;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.services.CustomerService;
import sit.int204.classicmodelsservice.utils.ListMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("/{customerId}")
    public SimpleCustomerDto getSimpleCustomerById(@PathVariable Integer customerId) {
        return modelMapper.map(customerService.getCustomerById(customerId), SimpleCustomerDto.class);
    }

    @GetMapping("")
    public List<SimpleCustomerDto> getCustomers() {
        List<Customer> customerList = customerService.getCustomer();
        List<SimpleCustomerDto> simpleCustomerDtoList = customerList.stream().map(c -> modelMapper.map(c, SimpleCustomerDto.class)).collect(Collectors.toList());
        return simpleCustomerDtoList;
    }

    @GetMapping("/pages")
    public PageDto<SimpleCustomerDto> getCustomersPages(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<Customer> customerList = customerService.getCustomerPages(page, size);
        return listMapper.toPageDto(customerList, SimpleCustomerDto.class, modelMapper);
    }
}
