package tech.wenisch.flightstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightStatusApplication.class, args);
	}

}
