package cn.backurl.bing.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <br>
 * 必应壁纸下载定时器
 * </br>
 *
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
        System.out.println("执行了定时器");
    }
}
