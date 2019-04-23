package cn.backurl.bing.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * mybatis plus分页插件配置
 * </p>
 *
 * @author: akid
 * @create: 2019-04-24 00:48
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("cn.backurl.bing.dao.*")
@Log4j2
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("初始化mp分页插件...");
        return new PaginationInterceptor();
    }
}