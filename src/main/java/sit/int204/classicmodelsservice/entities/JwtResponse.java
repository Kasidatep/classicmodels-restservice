package sit.int204.classicmodelsservice.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {
    private final String jwttoken;

}
