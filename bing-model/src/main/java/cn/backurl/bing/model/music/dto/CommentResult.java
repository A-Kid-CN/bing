package cn.backurl.bing.model.music.dto;

import cn.backurl.bing.model.music.MusicComment;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 网易云请求实体
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 21:52
 **/
@Data
public class CommentResult {

    /**
     * 评论列表
     */
    private List<MusicComment> comments;
    /**
     * 页码
     */
    private int pageNop;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 总数
     */
    private int total;

    /**
     * 后面是否还有数据
     */
    private boolean more;
}
