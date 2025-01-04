package teamyc.recordpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RecordPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordPetApplication.class, args);
    }
}
