package cn.backurl.bing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 暂时用于测试swagger2
 * 访问地址：http://http://localhost:8080/swagger-ui.html#/
 */
@RestController
@Api("测试Api接口文档")
@RequestMapping("/test")
public class TestController {
    @ApiOperation("返回信息展示")
    @RequestMapping("/hello")
    public String hello(){
        return "hello swagger2";
    }
}
