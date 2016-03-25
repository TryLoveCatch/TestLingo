package cn.lingox.android.bin.api;

import cn.lingox.android.bin.detail.InfoDetail;
import cn.lingox.android.bin.local.info.InfoLocalListRsp;
import cn.lingox.android.bin.meizi.info.InfoMeiziData;
import cn.lingox.android.bin.user.info.InfoUserRsp;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lipeng21 on 2015/11/9.
 */
public interface Apis {

    int PAGESIZE = 20;

    String page = "page";
    String ver = "ver";


    String emailOrUsername = "emailOrUsername";
    String password = "password";



    String type_meizi = "福利";
    String type_video = "休息视频";
    String type_android = "Android";
    String type_ios = "iOS";
    String type_expand = "拓展资源";
    String type_recommend = "瞎推荐";
    String type_app = "App";

    //根据日期获取当天所有数据
    @GET("day/{year}/{month}/{day}")
    Observable<InfoDetail> getAllDataByDate(@Path("year") String pYear, @Path("month") String pMonth, @Path("day") String pDay);



    //分页获取妹子图片
//    @GET("data/福利/{pageSize}/{page}")
//    Observable<InfoMeiziData> getMeizi(@Path("page") int pPage, @Path("pageSize") int pPageSize);
//    Call<InfoMeizi> getMeizi(@Path("page") int pPage);

    @GET("data/{type}/{pageSize}/{page}")
    Observable<InfoMeiziData> getDataByType(@Path("page") int pPage, @Path("pageSize") int pPageSize, @Path("type") String pType);



    //==============================user start=====================================
    //用户登录
    @FormUrlEncoded
    @POST("api/v1/users/login")
    Observable<InfoUserRsp> login(@Field("emailOrUsername")String emailOrUsername , @Field("password")String password);
    //==============================user end=====================================

    //==============================home start=====================================
    //获取local experiences数据
    @FormUrlEncoded
    @POST("Path/getAllPaths")
    Observable<InfoLocalListRsp> loadLocalList(@Field("ver")String ver , @Field("page")String page);
    //==============================home end=====================================
}
