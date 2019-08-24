package cn.backurl.bing.service.music.impl;

import cn.backurl.bing.constant.MusicConstant;
import cn.backurl.bing.dao.music.MusicSongMapper;
import cn.backurl.bing.model.music.MusicSong;
import cn.backurl.bing.service.music.MusicSongService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>
 * 歌曲详情表 服务实现类
 * </p>
 *
 * @author akid
 * @since 2019-08-23
 */
@Service
@Slf4j
public class MusicSongServiceImpl extends ServiceImpl<MusicSongMapper, MusicSong> implements MusicSongService {

    @Override
    public MusicSong getSongDetailFromWYY(long songId) {
        String url = MusicConstant.DETAIL_URL + songId;
        CloseableHttpClient httpclient = HttpClients.custom().disableAutomaticRetries().build();

        HttpPost post = new HttpPost(url);
        post.getMethod();


        try {
            CloseableHttpResponse response = httpclient.execute(post);
            int iGetResultCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String strResult;
            if (iGetResultCode >= 200 && iGetResultCode < 303) {
                strResult = EntityUtils.toString(entity, "gbk");
            } else {
                strResult = "http请求错误：" + iGetResultCode;
            }
            JSONObject jsonObject = JSONObject.parseObject(strResult);
            Integer code = jsonObject.getInteger("code");
            if (code == null || !code.equals(200)) {
                log.info("网易请求失败了：{}", code);
                return null;
            }
            log.info(strResult);
            JSONArray songArr = jsonObject.getJSONArray("songs");
            JSONObject songObj = (JSONObject) songArr.get(0);
            String songName = songObj.getString("name");
            long publishTime = songObj.getLong("publishTime");
            JSONObject albumObj = songObj.getJSONObject("al");
            String albumName = albumObj.getString("name");
            String picUrl = albumObj.getString("picUrl");

            String songer;
            try {
                songer = ((JSONObject) songObj.getJSONArray("ar").get(0)).getString("name");
            } catch (NullPointerException e) {
                log.error("获取歌手异常", e);
                songer = "未知歌手";
            }

            MusicSong musicSong = new MusicSong();
            musicSong.setName(songName);
            musicSong.setSongId(songId);
            musicSong.setSinger(songer);
            musicSong.setAlbumName(albumName);
            musicSong.setAlbumPicUrl(picUrl);
            Instant instant = Instant.ofEpochMilli(publishTime);
            ZoneId zone = ZoneId.systemDefault();
            musicSong.setPublishTime(LocalDateTime.ofInstant(instant, zone));
            musicSong.setUpdateTime(LocalDateTime.now());
            musicSong.setCreateTime(LocalDateTime.now());
            return musicSong;
        } catch (Exception e) {
            log.error("获取歌曲详情失败", e);
            return null;
        }
    }
}
