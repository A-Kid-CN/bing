package cn.backurl.bing.model.music;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 网易云音乐用户信息表
 * </p>
 *
 * @author akid
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_user")
public class MusicUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 性别（0：位置，1：男，2：女）
     */
    private Boolean gender;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 网易云id
     */
    private Long wyyId;

    /**
     * 头像地址
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 粉丝数
     */
    private Integer followeds;

    /**
     * 关注数
     */
    private Integer follows;

    /**
     * 签名
     */
    private String signature;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 用户主页背景图地址
     */
    @TableField("backgroundUrl")
    private String backgroundUrl;

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


}
