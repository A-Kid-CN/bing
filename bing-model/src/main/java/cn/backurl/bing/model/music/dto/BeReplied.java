package cn.backurl.bing.model.music.dto;

import lombok.Data;

/**
 * <p>
 * 在评论中引用的评论
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 22:06
 **/
@Data
public class BeReplied {

    private WYUser user;

    private int beRepliedCommentId;

    private String content;

    private int status;

    private String expressionUrl;
}
