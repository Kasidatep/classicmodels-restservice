package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleEmployeeDto {
    private String lastName;
    private String firstName;

    public String getName() {
        return firstName + ' ' + lastName;
    }
}
