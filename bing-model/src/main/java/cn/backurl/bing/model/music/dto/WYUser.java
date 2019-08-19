package cn.backurl.bing.model.music.dto;

import lombok.Data;

/**
 * <p>
 * 网易云用户信息
 * </p>
 *
 * @author: akid
 * @create: 2019-08-19 22:07
 **/
@Data
public class WYUser {
    /*
    "user":{
            "locationInfo":null,
            "liveInfo":null,
            "authStatus":0,
            "avatarUrl":"http://p2.music.126.net/6fuy676o7b7XLZTIwHIFEQ==/109951164217190078.jpg",
            "experts":null,
            "vipType":0,
            "nickname":"清几时",
            "userId":1476953116,
            "userType":0,
            "vipRights":null,
            "remarkName":null,
            "expertTags":null
        },
     */

    private String locationInfo;

    private String liveInfo;

    private String vipRights;

    private int vipType;

    private String nickname;

    private int authStatus;

    private String avatarUrl;

    private long userId;

    private String experts;

    private String expertTags;

    private int userType;

    private String remarkName;
}
