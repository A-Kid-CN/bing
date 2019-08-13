package music;

import cn.backurl.bing.service.music.MusicCommentService;
import cn.backurl.bing.service.music.impl.MusicCommentServiceImpl;
import org.junit.Test;

public class MusicComentServiceTest {

    @Test
    public void testGetComments() {
        MusicCommentService musicCommentService = new MusicCommentServiceImpl();
        musicCommentService.listComment("411214279", 1, 100);
    }

}
