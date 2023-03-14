package sit.int204.classicmodelsservice;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import sit.int204.classicmodelsservice.utils.ListMapper;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }
}
