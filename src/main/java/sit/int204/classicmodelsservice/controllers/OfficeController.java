package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.services.OfficeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    @Autowired
    public OfficeService service;

    @GetMapping("")
    public List<Office> getAllOffice() {
        return service.getOffice();
    }

    @GetMapping("/{officeCode}")
    public Office getOfficeById(@PathVariable String officeCode) {
        return service.getOfficeById(officeCode);
    }

    @PostMapping("")
    public void addOffice(@RequestBody Office office) {
        service.addOffice(office);
    }

    @PutMapping("/{officeCode}")
    public Office updateOffice(@PathVariable String officeCode, @RequestBody Office office) {
        return service.updateOffice(officeCode, office);
    }

    @DeleteMapping("/{officeCode}")
    public Office deleteOffice(@PathVariable String officeCode) {
        return service.deleteOffice(officeCode);
    }

    @GetMapping("/{officeCode}/employees")
    public Set<Employee> getEmployeeByOfficeCode(@PathVariable String officeCode) {
        return service.getEmployeeByOfficeCode(officeCode);
    }

    @PostMapping("/{officeCode}/employees")
    public void addEmployee(@PathVariable String officeCode, @RequestBody Employee employee) {
        service.addEmployee(officeCode, employee);
    }
}
