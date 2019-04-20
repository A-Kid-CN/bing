package generator.wallpaper.service.impl;

import generator.wallpaper.entity.Wallpaper;
import generator.wallpaper.mapper.WallpaperMapper;
import generator.wallpaper.service.IWallpaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 必应壁纸数据表 服务实现类
 * </p>
 *
 * @author akid
 * @since 2019-04-20
 */
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper> implements IWallpaperService {

}
