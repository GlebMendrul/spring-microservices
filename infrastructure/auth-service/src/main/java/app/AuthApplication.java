package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by gleb on 3/18/2017.
 */
@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthApplication.class, args);
    }
}
