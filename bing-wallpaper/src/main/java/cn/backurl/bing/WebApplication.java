package cn.backurl.bing;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"cn.backurl.bing"})
@MapperScan("cn.backurl.bing.dao.*")
@Log4j2
public class WebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		log.info("spring启动完毕");
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:/usr/local/bing/");

		log.info("注入文件虚拟路径");
	}

}

