package cn.backurl.bing.service.music;

import cn.backurl.bing.model.music.MusicSong;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 歌曲详情表 服务类
 * </p>
 *
 * @author akid
 * @since 2019-08-23
 */
public interface MusicSongService extends IService<MusicSong> {

    /**
     * <p>
     * 功能描述: 从网易云获取歌曲详情
     * </p>
     *
     * @param songId
     * @Author: akid
     * @Date: 2019-08-23 00:41
     * @Return: cn.backurl.bing.model.music.MusicSong
     */
    MusicSong getSongDetailFromWYY(long songId);
}
