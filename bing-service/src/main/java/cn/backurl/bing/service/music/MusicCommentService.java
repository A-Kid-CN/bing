package cn.backurl.bing.service.music;

import cn.backurl.bing.model.music.MusicComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 网易云音乐评论数据表 服务类
 * </p>
 *
 * @author akid
 * @since 2019-08-13
 */
public interface MusicCommentService extends IService<MusicComment> {


    /**
     * <br>
     * 功能描述: 获取歌曲的评论列表
     * </br>
     *
     * @param songId   歌曲id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return:java.util.List<cn.backurl.bing.model.music.MusicComment>
     * @since: 1.0.0
     * @Author:akid
     * @Date: 2019/8/13 20:45
     */
    List<MusicComment> listComment(String songId, Integer pageNo, Integer pageSize);
}
