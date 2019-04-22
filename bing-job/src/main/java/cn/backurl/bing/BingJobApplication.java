package cn.backurl.bing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.backurl.bing.job"})
@MapperScan(basePackages = "cn.backurl.bing.dao")
public class BingJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingJobApplication.class, args);
	}

}

