import cn.backurl.bing.service.music.MusicCommentService;
import cn.backurl.bing.service.music.impl.MusicCommentServiceImpl;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 21:13
 **/
public class MusicCommentServiceTest {

    @Test
    public void listCOPmmentsTest(){

        MusicCommentService musicCommentService = new MusicCommentServiceImpl();
        long songId = 440208476l;
        musicCommentService.getCommentFromWYY(songId, 1, 5);
    }

}
