package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.repositories.OfficeRepository;

import java.util.List;
import java.util.Set;

@Service
public class OfficeService {
    @Autowired
    public OfficeRepository repository;

    public List<Office> getOffice() {
        return repository.findAll();
    }

    public Office getOfficeById(String officeCode) {
        return repository.findById(officeCode).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public void addOffice(Office office) {
        repository.saveAndFlush(office);
    }

    public Office updateOffice(String officeCode, Office office) {
        if (repository.existsById(officeCode)) {
            deleteOffice(officeCode);
            addOffice(office);
            return getOfficeById(officeCode);
        }
        return null;
    }

    public Office deleteOffice(String officeCode) {
        Office office = repository.findById(officeCode).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Office Id " + officeCode + " DOES NOT EXIST !!!"));
        repository.delete(office);
        return office;
    }

    public Set<Employee> getEmployeeByOfficeCode(String officeCode) {
        Office office = repository.findById(officeCode).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Office Id " + officeCode + " DOES NOT EXIST !!!"));
        System.out.println(office.getEmployees());
        return office.getEmployees();
    }
}
