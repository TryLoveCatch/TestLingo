package cn.lingox.android.bin.user.info;

import java.util.ArrayList;
import java.util.Date;

import cn.lingox.android.share.info.InfoAppBase;

/**
 * Created by lipeng21 on 2016/3/15.
 */
public class InfoUser extends InfoAppBase<InfoUser.Data>{
    public static class Data{
        public String id;//用户id---用户的唯一标示
        public String username;//登录账户-----也可用作唯一标示
        public String nickname;//用户昵称
        public String email;//用户邮箱----未做邮箱有效验证
        public String country;//国家
        public String province;//省份
        public String city;//城市
        public String speak;//语言
        public String avatar;//头像链接
        public String dateOfBirth;//生日
        public String gender;//性别
        public String profession;//职业
        public ArrayList<String> interests = new ArrayList<>();//兴趣
        public String signature;//自我介绍
        public double[] loc;//地理位置-----
        public String locString;//纬度
        public String loginTime;//经度
        public boolean online;//是否在线
        public boolean homeStay;//提供家庭住宿
        public boolean homeMeal;//提供家庭餐
        public boolean localGuide;//本地向导
        public int relation;

        public Date createdAt;//注册时间
        public Date updatedAt;//注册时间
        public String visited;

        public int localsCount;
        public int travelsCount;
        public int followersCount;
    }
}
