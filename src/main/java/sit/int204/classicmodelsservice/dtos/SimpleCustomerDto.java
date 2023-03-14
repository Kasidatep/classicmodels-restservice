package sit.int204.classicmodelsservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDto {
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private String salesPerson;
    private SimpleEmployeeDto saleRefEmployees;
    private String saleRefEmployeesFirstName;
    private SimpleEmployeeDto sale;
    @JsonIgnore
    private SimpleEmployeeDto saleRef;

    public String getSalesPerson() {
        return saleRef == null ? " - " : saleRef.getName();
    }
}
