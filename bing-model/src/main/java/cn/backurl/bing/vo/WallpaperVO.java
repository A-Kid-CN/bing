package cn.backurl.bing.vo;

import cn.backurl.bing.model.wallpaper.Wallpaper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 壁纸vo
 * （与前端交互的对象，数据脱敏）
 * </p>
 *
 * @author: akid
 * @create: 2019-04-24 00:13
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WallpaperVO implements Serializable {

    private Long id;

    /**
     * 图片的日期
     */
    private String date;

    /**
     * 图片名
     */
    private String name;


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
    private Integer love;

    /**
     * 下载数
     */
    private Integer download;

    public static WallpaperVO getWallpaperVO(Wallpaper wallpaper){
        WallpaperVO wallpaperVO = new WallpaperVO();
        if(null != wallpaper){
            wallpaperVO.setId(wallpaper.getId());
            wallpaperVO.setName(wallpaper.getName());
            wallpaperVO.setClick(wallpaper.getClick());
            wallpaperVO.setCopyRight(wallpaper.getCopyRight());
            wallpaperVO.setDate(wallpaper.getDate());
            wallpaperVO.setDownload(wallpaper.getDownload());
            wallpaperVO.setLove(wallpaper.getLove());
        }
        return wallpaperVO;
    }

}
