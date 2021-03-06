package cn.backurl.bing.music;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"cn.backurl.bing"})
@MapperScan("cn.backurl.bing.dao")
@Slf4j
public class MusicApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
		log.info("spring启动完毕");
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/image/**").addResourceLocations("file:/usr/local/bing/");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");


		log.info("注入文件虚拟路径");
	}

}

