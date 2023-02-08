package sit.int204.classicmodelsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.services.OfficeService;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    public OfficeService service;

    @GetMapping("")
    public List<Office> getAllOffice(){
        return service.getOffice();
    }

    @GetMapping("/{officeCode}")
    public Office getOfficeById(@PathVariable String officeCode){
        return service.getOfficeById(officeCode);
    }
}
