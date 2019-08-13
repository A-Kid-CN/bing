package cn.backurl.bing.constant;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>
 * 网易云音乐常量类
 * </br>
 *
 * @author: akid
 * @create: 2019/8/13
 */
@Slf4j
public class MusicConstant {

    /**
     * 评论url
     * (id直接拼接)
     * 其他参数：
     * 页大小：limit
     * 偏移量：offset
     */
    public static final String COMMENT_URL = "http://music.163.com/api/v1/resource/comments/R_SO_4_";

    /**
     * 歌曲详情url
     */
    public static final String DETAIL_URL = "https://api.imjad.cn/cloudmusic/?type=detail&br=128000&id=";


}
