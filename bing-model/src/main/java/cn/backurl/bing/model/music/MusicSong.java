package cn.backurl.bing.model.music;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 歌曲详情表
 * </p>
 *
 * @author akid
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_song")
public class MusicSong implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 歌曲id
     */
    private Long songId;

    /**
     * 歌曲名称
     */
    private String name;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 专辑名称
     */
    private String albumName;

    /**
     * 专辑封面图
     */
    private String albumPicUrl;

    /**
     * 专辑推出时间
     */
    private LocalDateTime publishTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
