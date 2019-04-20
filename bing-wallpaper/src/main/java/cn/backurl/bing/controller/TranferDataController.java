package cn.backurl.bing.controller;

import cn.backurl.bing.dao.wallpaper.WallpaperMapper;
import cn.backurl.bing.dao.wallpaper.WallpaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 迁移数据
 * </p>
 *
 * @author: akid
 * @create: 2019-04-21 01:10
 **/
//@RestController("/tranfer")
@Slf4j
public class TranferDataController {


    @Autowired
    private WallpaperMapper wallpaperService;

    @RequestMapping("/do")
    public Object tranfer(){
        return wallpaperService.selectById(1L);
    }
}
