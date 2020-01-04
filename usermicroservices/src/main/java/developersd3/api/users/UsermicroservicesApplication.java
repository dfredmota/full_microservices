package developersd3.api.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UsermicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermicroservicesApplication.class, args);
    }

}
