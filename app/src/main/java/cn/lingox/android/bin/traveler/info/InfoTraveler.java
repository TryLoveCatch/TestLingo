package cn.lingox.android.bin.traveler.info;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.util.ArrayList;

import cn.lingox.android.framework.data.InfoBase;

/**
 * Created by lipeng21 on 2016/3/25.
 */
@Table("local")
public class InfoTraveler extends InfoBase{
    @PrimaryKey(AssignType.BY_MYSELF)
    public String id;//内容的唯一标示
    public String user_id;//发布者的id
    public String country;//旅行地点--国家
    public String province;//旅行地点--省份
    public String city;//旅行地点--城市
    public String text;//详细描述
    public ArrayList<String> tags;//标签
    public long startTime;//开始时间
    public long endTime;//结束时间
    public String provide;//可提供
//    public ArrayList<User> likedUsers;//likeUsers的用户
//    public ArrayList<TravelComment> comments;//评论的用户
    public String createdAt;//创建时间
    public String updatedAt;// 修改时间，自动生成
}
