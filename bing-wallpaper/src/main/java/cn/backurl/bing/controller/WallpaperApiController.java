package cn.backurl.bing.controller;

import cn.backurl.bing.model.wallpaper.Wallpaper;
import cn.backurl.bing.result.AjaxResult;
import cn.backurl.bing.result.ResultCode;
import cn.backurl.bing.service.wallpaper.WallpaperService;
import cn.backurl.bing.vo.WallpaperVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 壁纸api控制层
 * (只提供ajax请求数据)
 * </p>
 *
 * @author: akid
 * @create: 2019-04-24 00:04
 **/
@RestController
@RequestMapping("/wallpaper")
@Log4j2
public class WallpaperApiController {

    @Autowired
    private WallpaperService wallpaperService;

    @GetMapping("/get/{id}")
    public Object getOne(@PathVariable(value="id")Long id) {
        if (null != id) {
            Wallpaper wallpaper = wallpaperService.getById(id);
            return AjaxResult.success(WallpaperVO.getWallpaperVO(wallpaper));
        }
        return AjaxResult.failure(ResultCode.ParamException);
    }

}
