package cn.backurl.bing.model.music;

import cn.backurl.bing.model.music.dto.BeReplied;
import cn.backurl.bing.model.music.dto.WYUser;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 网易云音乐评论数据表
 * </p>
 *
 * @author akid
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_comment")
public class MusicComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论的歌曲id
     */
    private Long songId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime time;

    /**
     * 评论like数
     */
    @TableField("likedCount")
    private Integer likedCount;

    /**
     * 评论人的网易云id
     */
    private Long wyyId;

    /**
     * 评论人的昵称
     */
    private String nickname;

    /**
     * 原始json
     */
    @TableField("original_json")
    private String originalJson;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 网易云用户信息
     */
    @TableField(exist = false)
    private WYUser wyUser;

    /**
     * 被引用的评论列表
     */
    @TableField(exist = false)
    private List<BeReplied> replieds;


}
