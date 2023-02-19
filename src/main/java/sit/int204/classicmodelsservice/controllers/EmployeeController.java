package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("")
    public List<Employee> getAllEmployee() {
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/{id}/offices")
    public Office getOfficeByEmployeeId(@PathVariable String id) {
        return service.getOfficeByEmployeeId(id);
    }

    @GetMapping("/{id}/report-to")
    public Employee getEmployeeReportTo(@PathVariable String id) {
        return service.getEmployeeReportTo(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        service.deleteEmployee(id);
    }

}
