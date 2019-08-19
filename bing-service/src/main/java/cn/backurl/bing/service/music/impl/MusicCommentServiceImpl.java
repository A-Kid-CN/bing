package cn.backurl.bing.service.music.impl;


import cn.backurl.bing.constant.MusicConstant;
import cn.backurl.bing.dao.music.MusicCommentMapper;
import cn.backurl.bing.model.music.MusicComment;
import cn.backurl.bing.model.music.dto.BeReplied;
import cn.backurl.bing.model.music.dto.CommentResult;
import cn.backurl.bing.model.music.dto.WYUser;
import cn.backurl.bing.service.music.MusicCommentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.java.emoji.EmojiConverter;
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
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 网易云音乐评论数据表 服务实现类
 * </p>
 *
 * @author akid
 * @since 2019-08-13
 */
@Service
@Slf4j
public class MusicCommentServiceImpl extends ServiceImpl<MusicCommentMapper, MusicComment> implements MusicCommentService {
    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();


    @Override
    public CommentResult getCommentFromWYY(Long songId, Integer pageNo, Integer pageSize) {
        if (songId == null || null == pageSize || null == pageNo) {
            log.info("error params...");
            CommentResult commentResult = new CommentResult();
            commentResult.setMore(false);
            commentResult.setTotal(0);
            return commentResult;
        }
        CommentResult commentResult = new CommentResult();

        String url = MusicConstant.COMMENT_URL + songId + "?&limit=" + pageSize + "&offset=" + (pageNo - 1) * pageSize;
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
            } else if (iGetResultCode >= 400 && iGetResultCode < 500) {
                strResult = "请求的目标地址不存在：" + iGetResultCode;
            } else {
                strResult = "请求错误：" + iGetResultCode;
            }

            /*
             格式如下：
            "isMusician":false,
            "userId":-1,
            "topComments":Array[0],
            "moreHot":true,
            "hotComments":Array[15],
            "code":200,
            "comments":Array[5],
            "total":211415,
            "more":true

             */

            JSONObject jsonObject = JSONObject.parseObject(strResult);
            Integer code = jsonObject.getInteger("code");
            if (code == null || !code.equals(200)) {
                log.info("请求失败：{}", code);
                return null;
            }
            JSONArray commonts = jsonObject.getJSONArray("comments");
            //总数
            Integer total = jsonObject.getInteger("total");
            Boolean more = jsonObject.getBoolean("more");

            List<MusicComment> musicComments = new ArrayList<>();
            commonts.forEach(commont -> {
                MusicComment musicComment = new MusicComment();

                JSONArray beRepliedArray = ((JSONObject) commont).getJSONArray("beReplied");
                if (!beRepliedArray.isEmpty()) {
                    List<BeReplied> beReplieds = new ArrayList<>();
                    //被引用的评论
                    beRepliedArray.forEach(item -> {
                        BeReplied beReplied = ((JSONObject) item).toJavaObject(BeReplied.class);
                        beReplieds.add(beReplied);
                    });
                    musicComment.setReplieds(beReplieds);
                }

                //评论的用户信息
                JSONObject wyUserObj = ((JSONObject) commont).getJSONObject("user");
                WYUser wyUser = wyUserObj.toJavaObject(WYUser.class);
                musicComment.setWyUser(wyUser);


                String content = ((JSONObject) commont).getString("content");

                long time = ((JSONObject) commont).getLong("time");
                Integer likedCount = ((JSONObject) commont).getInteger("likedCount");

                Instant instant = Instant.ofEpochMilli(time);
                ZoneId zone = ZoneId.systemDefault();

                musicComment.setSongId(songId);
                musicComment.setWyyId(wyUser.getUserId());
                musicComment.setNickname(wyUser.getNickname());
                musicComment.setContent(emojiConverter.toAlias(content));
                musicComment.setTime(LocalDateTime.ofInstant(instant, zone));
                musicComment.setLikedCount(likedCount);
                musicComment.setOriginalJson(emojiConverter.toAlias(((JSONObject) commont).toJSONString()));
                musicComment.setUpdateTime(LocalDateTime.now());
                musicComment.setCreateTime(LocalDateTime.now());

                musicComments.add(musicComment);
            });
            commentResult.setTotal(total);
            commentResult.setMore(more);
            commentResult.setComments(musicComments);

        } catch (IOException e) {
            log.error("请求网易云评论数据异常", e);
            return null;
        }
        log.info(JSON.toJSONString(commentResult));

        return commentResult;
    }

    public static void main(String[] args) {

    }
}
