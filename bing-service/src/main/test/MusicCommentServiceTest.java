import cn.backurl.bing.model.music.MusicSong;
import cn.backurl.bing.service.music.MusicCommentService;
import cn.backurl.bing.service.music.MusicSongService;
import cn.backurl.bing.service.music.impl.MusicCommentServiceImpl;
import cn.backurl.bing.service.music.impl.MusicSongServiceImpl;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 21:13
 **/
@Slf4j
public class MusicCommentServiceTest {

    @Test
    public void listCOPmmentsTest(){
//
//        MusicCommentService musicCommentService = new MusicCommentServiceImpl();
        long songId = 440208476l;
//        musicCommentService.getCommentFromWYY(songId, 1, 5);

        MusicSongService songService = new MusicSongServiceImpl();
        MusicSong song = songService.getSongDetailFromWYY(songId);
        log.info(JSON.toJSONString(song));
    }

}
