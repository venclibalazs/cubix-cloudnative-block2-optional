package hu.cubix.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecondOptionalFrontend {

	public static void main(String[] args) {
		SpringApplication.run(SecondOptionalFrontend.class, args);
	}

}
