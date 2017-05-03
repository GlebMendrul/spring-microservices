package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Created by gleb on 3/19/2017.
 */
@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
