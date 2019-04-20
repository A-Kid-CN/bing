package generator.wallpaper.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 必应壁纸数据表
 * </p>
 *
 * @author akid
 * @since 2019-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Wallpaper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片的日期
     */
    private String date;

    /**
     * 图片名
     */
    private String name;

    /**
     * 原图路径
     */
    private String path;

    /**
     * 缩略图路径
     */
    private String miniPath;

    /**
     * 版权说明
     */
    private String copyRight;

    /**
     * 点击数
     */
    private Integer click;

    /**
     * 喜欢（收藏）数
     */
    private Integer like;

    /**
     * 下载数
     */
    private Integer download;

    /**
     * 必应原生json
     */
    private String original;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
