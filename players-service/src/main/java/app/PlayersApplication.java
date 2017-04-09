package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by gleb on 3/12/2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
public class PlayersApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PlayersApplication.class, args);
    }

}
