package cn.backurl.bing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.backurl.bing.job"})
public class BingJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingJobApplication.class, args);
	}

}

