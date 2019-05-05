package cn.backurl.bing.controller;

import cn.backurl.bing.domian.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.backurl.bing.util.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lichenyi
 * @date 2019-05-05 22:07
 */
@RestController
public class AdminController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @PostMapping("/admin/login")
    public ResponseEntity login(String username,String password){
        //todo 数据库逻辑
        User user = new User();
        user.setUsername("suxinying");
        String token = new JWTUtil().generateToken(user);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ResponseEntity.ok(token);
    }
}
