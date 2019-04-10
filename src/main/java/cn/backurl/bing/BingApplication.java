package cn.backurl.bing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages = {"cn.backurl.bing.redis"})
public class BingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingApplication.class, args);

	}

}

