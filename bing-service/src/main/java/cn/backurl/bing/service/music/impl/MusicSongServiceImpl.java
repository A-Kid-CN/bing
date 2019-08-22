package cn.backurl.bing.service.music.impl;

import cn.backurl.bing.dao.music.MusicSongMapper;
import cn.backurl.bing.model.music.MusicSong;
import cn.backurl.bing.service.music.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲详情表 服务实现类
 * </p>
 *
 * @author akid
 * @since 2019-08-23
 */
@Service
public class MusicSongServiceImpl extends ServiceImpl<MusicSongMapper, MusicSong> implements SongService {

}
