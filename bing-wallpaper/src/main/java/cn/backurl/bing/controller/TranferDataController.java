package cn.backurl.bing.controller;

import cn.backurl.bing.dao.wallpaper.WallpaperMapper;
import cn.backurl.bing.dao.wallpaper.WallpaperService;
import cn.backurl.bing.result.AjaxResult;
import cn.backurl.bing.result.ResultCode;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * <p>
 * 迁移数据
 * </p>
 *
 * @author: akid
 * @create: 2019-04-21 01:10
 **/
@RestController
@RequestMapping("/tranfer")
@Slf4j
public class TranferDataController {


    @Autowired
    private WallpaperService wallpaperService;

    /**
     * <p>
     * 功能描述:迁移壁纸数据到数据库
     * </p>
     *
     * @param basePath 文件基础路径
     * @param year     年份
     * @Author: akid
     * @Date: 2019-04-21 18:06
     * @Return: java.lang.Object
     */
    @PostMapping("/do")
    public Object tranfer(String basePath, String year) {
        if (StringUtils.isBlank(basePath) || StringUtils.isBlank(year)) {
            return AjaxResult.failure(ResultCode.ParamException);
        }
        int monthNum = 12;
        if (year.equals(2015)) {
            monthNum = 3;
        }
        for (int i = 12; monthNum > 0; monthNum--, i--) {
            File file = new File(basePath + "/" + year + "年/" + year + "Bing" + (i >= 10 ? i : "0" + i));
            if (file.isFile() && file.exists()) {
                File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
                if (files == null) {// 如果目录为空，直接退出
                    continue;
                }
                for(File item :files){
                    log.info(item.getName());

                }
            }
        }
        return AjaxResult.success();
    }
}
