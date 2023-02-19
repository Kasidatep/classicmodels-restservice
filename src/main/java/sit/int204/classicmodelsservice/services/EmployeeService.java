package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }

    public Employee getEmployeeById(String id) {
        return repository.findById(Integer.valueOf(id)).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee Id " + id + " DOES NOT EXIST !!!"));
    }

    public void addEmployee(Employee employee, Office office) {
        employee.setOffice(office);
        repository.saveAndFlush(employee);
    }

    public Employee updateEmployee(String id, Employee employee) {
        Employee oldEmployee = getEmployeeById(id);
        oldEmployee.setFirstName(employee.getFirstName());
        oldEmployee.setLastName(employee.getLastName());
        oldEmployee.setExtension(employee.getExtension());
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setJobTitle(employee.getJobTitle());
        repository.saveAndFlush(oldEmployee);
        return oldEmployee;
    }

    public void deleteEmployee(String id) {
        repository.deleteById(Integer.valueOf(id));
    }

    public Office getOfficeByEmployeeId(String id) {
        Employee employee = getEmployeeById(id);
        return employee.getOffice();
    }

    public Employee getEmployeeReportTo(String id) {
        Employee employee = getEmployeeById(id);
        return employee.getEmployees();
    }
}
