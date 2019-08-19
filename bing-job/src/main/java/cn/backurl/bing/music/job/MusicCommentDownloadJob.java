package cn.backurl.bing.music.job;

import cn.backurl.bing.model.music.MusicComment;
import cn.backurl.bing.model.music.dto.CommentResult;
import cn.backurl.bing.service.music.MusicCommentService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 音乐评论下载定时器
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 22:40
 **/
@Component
@EnableScheduling
@Slf4j
public class MusicCommentDownloadJob {
    @Autowired
    private MusicCommentService musicCommentService;

    /**
     * <p>
     * 功能描述: 1s执行一次
     * </p>
     *
     * @param
     * @Author: akid
     * @Date: 2019-08-19 22:41
     * @Return: void
     */
    @Scheduled(cron = "0/1 * * * * ? ")
    public void execute() {
        int pageNo = 1;
        int pageSize = 100;
        long songId = 440208476l;
        CommentResult commentResult = new CommentResult();
        commentResult.setMore(true);
        while (commentResult.isMore()) {
            log.info("当前页码：{}", pageNo);
            commentResult = musicCommentService.getCommentFromWYY(songId, pageNo, pageSize);
            handleCommentResult(commentResult);
            log.info("总数：{}", commentResult.getTotal());
            pageNo++;
        }
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
