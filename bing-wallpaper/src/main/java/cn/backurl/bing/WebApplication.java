package cn.backurl.bing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"cn.backurl.bing"})
@MapperScan("cn.backurl.bing.dao.*")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);

	}

}

