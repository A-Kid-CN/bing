package cn.backurl.bing.music.controller;

import cn.backurl.bing.model.music.MusicSong;
import cn.backurl.bing.result.AjaxResult;
import cn.backurl.bing.result.ResultCode;
import cn.backurl.bing.service.music.MusicSongService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 歌曲详情 控制层
 * </p>
 *
 * @author: akid
 * @create: 2019-08-23 01:02
 **/
@RestController
@RequestMapping("/music/song")
@Slf4j
public class MusicSongController {

    @Autowired
    private MusicSongService songService;

    /**
     * <p>
     * 功能描述: 开始爬去某一首歌曲对详情到数据库
     * </p>
     *
     * @param songId
     * @Author: akid
     * @Date: 2019-08-23 01:03
     * @Return: java.lang.Object
     */
    @GetMapping("/start")
    public Object start(Long songId) {
        if (songId == null) {
            return AjaxResult.failure(ResultCode.ParamException);
        }
        MusicSong song = songService.getSongDetailFromWYY(songId);
        try {
            Boolean result = songService.save(song);
            if (!result) {
                log.error("插入数据库异常");
                return AjaxResult.failure(ResultCode.SystemException, "爬去歌曲详情失败");
            }
        }catch (Exception e){
            log.error("可能歌曲已存在，导致异常");
        }

        return AjaxResult.success();
    }
}
