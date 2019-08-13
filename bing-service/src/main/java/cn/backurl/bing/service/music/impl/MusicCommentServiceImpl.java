package cn.backurl.bing.service.music.impl;


import cn.backurl.bing.constant.MusicConstant;
import cn.backurl.bing.dao.music.MusicCommentMapper;
import cn.backurl.bing.model.music.MusicComment;
import cn.backurl.bing.service.music.MusicCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
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


    @Override
    public List<MusicComment> listComment(String songId, Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(songId) || null == pageSize || null == pageNo) {
            log.info("error params...");
            return new ArrayList<>();
        }
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

            log.info(strResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
