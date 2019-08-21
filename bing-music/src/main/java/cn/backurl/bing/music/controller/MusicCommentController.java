package cn.backurl.bing.music.controller;

import cn.backurl.bing.model.music.MusicComment;
import cn.backurl.bing.model.music.dto.CommentResult;
import cn.backurl.bing.result.AjaxResult;
import cn.backurl.bing.result.ResultCode;
import cn.backurl.bing.service.music.MusicCommentService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 音乐评论控制层
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 23:54
 **/
@RestController
@RequestMapping("/music/comment")
@Slf4j
public class MusicCommentController {

    @Autowired
    private MusicCommentService musicCommentService;

    /**
     * <p>
     * 功能描述: 开始音乐评论爬虫
     * </p>
     *
     * @param songId
     * @Author: akid
     * @Date: 2019-08-19 23:56
     * @Return: java.lang.Object
     */
    @PostMapping("/start")
    public Object startCommentSpider(final Long songId) {
        if (songId == null) {
            return AjaxResult.failure(ResultCode.ParamException, "扑街，参数错了");
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 0;

                int pageNo = 1;
                int pageSize = 100;
                CommentResult commentResult = new CommentResult();
                commentResult.setMore(true);
                while (true) {
                    log.info("当前页码：{}", pageNo);
                    commentResult = musicCommentService.getCommentFromWYY(songId, pageNo, pageSize);
                    handleCommentResult(commentResult);
                    log.info("总数：{}", commentResult.getTotal());

                    if (!commentResult.isMore()) {
                        if (pageNo * pageSize < commentResult.getTotal()) {
                            try {
                                log.info("睡眠2s");
                                time ++;
                                log.info("第{}次重试ing...", time);
                                if(time>10){
                                    //尝试10次
                                    break;
                                }
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                log.error("睡眠异常", e);
                            }
                        } else {
                            break;
                        }
                    } else {
                        time = 0;
                        pageNo++;
                    }
                }
            }
        });
        thread.start();
        return AjaxResult.success();

    }

    /**
     * <p>
     * 功能描述: 处理评论
     * </p>
     *
     * @param commentResult
     * @Author: akid
     * @Date: 2019-08-19 22:47
     * @Return: void
     */
    private void handleCommentResult(CommentResult commentResult) {
        List<MusicComment> comments = commentResult.getComments();
        try {
            musicCommentService.saveBatch(comments);
        } catch (Exception e) {
            log.error("插入数据异常。", e);
            log.error("错误的commont：{}", JSONObject.toJSON(commentResult));
        }
    }
}
