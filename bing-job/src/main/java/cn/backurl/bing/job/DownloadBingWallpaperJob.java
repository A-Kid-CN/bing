package cn.backurl.bing.job;

import cn.backurl.bing.constant.SysConstant;
import cn.backurl.bing.dao.wallpaper.WallpaperMapper;
import cn.backurl.bing.model.wallpaper.Wallpaper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

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
    @Autowired
    private WallpaperMapper wallpaperMapper;
    private static final String BING_WEB = "http://cn.bing.com";
//    private static DownloadBingWallpaperJob downloadBingWallpaperJob;
//
//    @PostConstruct
//    public void init(){
//        downloadBingWallpaperJob = this;
//        downloadBingWallpaperJob.wallpaperMapper = this.wallpaperMapper;
//    }

    /**
     * <br>
     * 每天凌晨执行
     * </br>
     *
     * @author: akid
     * @create: 2019-04-18 00:06
     * {
     * "startdate": "20190421",
     * "fullstartdate": "201904211600",
     * "enddate": "20190422",
     * "url": "/th?id=OHR.LaysanAlbatross_ZH-CN2784683590_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp",
     * "urlbase": "/th?id=OHR.LaysanAlbatross_ZH-CN2784683590",
     * "copyright": "西北夏威夷群岛中途岛环礁上的黑背信天翁雏鸟 (© Jaymi Heimbuch/Minden Pictures)",
     * "copyrightlink": "http://www.bing.com/search?q=%E4%BF%A1%E5%A4%A9%E7%BF%81%E9%9B%8F%E9%B8%9F&form=hpcapt&mkt=zh-cn",
     * "title": "",
     * "quiz": "/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20190421_LaysanAlbatross%22&FORM=HPQUIZ",
     * "wp": true,
     * "hsh": "8c826b201b83cb53438b539634f1eef5",
     * "drk": 1,
     * "top": 1,
     * "bot": 1,
     * "hs": []
     * }
     */
    @Scheduled(cron = "0 58 0 * * ?")
    public void execute() {
        Wallpaper wallpaper = new Wallpaper();
        String str = getDataFormBing("js", 0, 1, "zh-CN");
        JSONObject jsonObj = (JSONObject) JSON.parse(str);
        JSONObject wallpaperInfo = (JSONObject) jsonObj.getJSONArray("images").get(0);
        wallpaper.setCopyRight(wallpaperInfo.getString("copyright"));
        wallpaper.setDate(wallpaperInfo.getString("enddate"));
        wallpaper.setDownload(0);
        wallpaper.setClick(0);
        wallpaper.setLove(0);
        wallpaper.setMiniPath(BING_WEB + wallpaperInfo.getString("url"));
        wallpaper.setPath(BING_WEB + wallpaperInfo.getString("url"));
        wallpaper.setName(wallpaperInfo.getString("fullstartdate"));
        wallpaper.setGmtCreate(new Date());
        wallpaper.setOriginal(wallpaperInfo.toString());
        wallpaper.setGmtModified(new Date());
        try {
            int result = wallpaperMapper.insert(wallpaper);
        }catch (Exception e){
            e.printStackTrace();
        }

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
    public static String getDataFormBing(String format, Integer idx, Integer n, String mkt) {
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
