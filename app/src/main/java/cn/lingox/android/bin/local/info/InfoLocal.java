package cn.lingox.android.bin.local.info;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.util.ArrayList;
import java.util.Date;

import cn.lingox.android.bin.user.info.InfoUser;
import cn.lingox.android.framework.data.InfoBase;

/**
 * Created by lipeng21 on 2016/3/25.
 */
@Table("local")
public class InfoLocal extends InfoBase{
    @PrimaryKey(AssignType.BY_MYSELF)
    public String id;//id---唯一标示
    public String user_id;//创建用户的id
    public String text;//内容
    public boolean passed;//审核状态
//    public PathCost cost;//花费---多种类型的花费，如gift
    public String title;//标题
    public long dateTime;//开始时间
    public long createdTime;//不知道什么时间
    public String availableTime;//可举办活动的时间
    public long endDateTime;//结束时间
    public int capacity;//可接待人数
    public String image;    // ArrayList of image URLs
    public String image11;    // ArrayList of image URLs---1:1
    public String image21;    // ArrayList of image URLs---2:1
    public String chosenCountry;//国家
    public String province;//省份
    public String chosenCity;//城市
    public ArrayList<InfoUser> acceptedUsers;//参加活动的用户
//    public ArrayList<Comment> comments;//评论
    public Date createdAt;//创建时间
//    public int type;//local：1 or travel:2（已将local和travel分开）
    public String hxGroupId;//环信id---群聊
    // Variables that are not linked to the Server
    public String nonDBLocationString;
    public String detailAddress;//地址----如新中东街3号
    public String latitude = "";//经度
    public String longitude = "";//纬度
    public ArrayList<String> tags;//标签
}
