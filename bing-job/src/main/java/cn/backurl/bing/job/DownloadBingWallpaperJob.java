package cn.backurl.bing.job;

import cn.backurl.bing.constant.SysConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <br>
 * 必应壁纸下载定时器
 * </br>
 *
 * @TODO 加上执行记录、分布式环境下只执行一次的功能
 * @author: akid
 * @create: 2019-04-14 15:07
 */
@Component
@EnableScheduling
public class DownloadBingWallpaperJob {

    /**
     * <br>
     * 每天凌晨执行
     * </br>
     *
     * @author: akid
     * @create: 2019-04-18 00:06
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void execute() {
        //1、拉取数据
        String str = getDataFormBing("js", 0, 8, "zh-CN");
        System.out.println(str);

    }

    /**
     * <p>
     * 功能描述: 从必应上面拉取壁纸
     * </p>
     *
     * @param format 返回数据格式，不存在返回xml格式
     *               js (一般使用这个，返回json格式)
     *               xml（返回xml格式）
     * @param idx    请求图片截止天数
     *               <p>
     *               0 今天
     *               -1 截止中明天 （预准备的）
     *               1 截止至昨天，类推（目前最多获取到7天前的图片）
     * @param n      1-8 返回请求数量，目前最多一次获取8张
     * @param mkt    地区
     *               <p>
     *               zh-CN
     * @Author: akid
     * @Date: 2019-04-18 22:36
     * @Return: java.lang.String
     */
    String getDataFormBing(String format, Integer idx, Integer n, String mkt) {
        if (StringUtils.isNotBlank(format) && idx != null && n != null && mkt != null) {
            String param = "?format=" + format + "&idx=" + idx + "&n=" + n + "&mkt=" + mkt;
            HttpPost post = new HttpPost(SysConstant.BING_URL + param);
            HttpClient client = HttpClientBuilder.create().build();
            try {
                HttpResponse response = client.execute(post);
                int code = response.getStatusLine().getStatusCode();
                if (code >= 400) {
                    return null;
                }
                return EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                //TODO 打log
            }
        }
        return null;
    }
}
