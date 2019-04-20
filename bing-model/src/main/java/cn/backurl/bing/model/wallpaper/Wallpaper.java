package cn.backurl.bing.model.wallpaper;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 必应壁纸实体类
 * </p>
 *
 * @author akid
 * @since 2019-04-20
 */
@Data
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
     * 必应原生json
     */
    private String original;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;


}
