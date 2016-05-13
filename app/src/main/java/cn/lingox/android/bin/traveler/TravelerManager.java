package cn.lingox.android.bin.traveler;

import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.lingox.android.bin.api.ApiManager;
import cn.lingox.android.bin.api.Apis;
import cn.lingox.android.bin.traveler.info.InfoTravelerListRsp;
import cn.lingox.android.bin.user.info.InfoUserRsp;
import cn.lingox.android.dao.DaoManager;
import cn.lingox.android.dao.InfoDaoHome;
import cn.lingox.android.dao.InfoDaoMeizi;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.event.EventTravelerList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class TravelerManager {

    private static TravelerManager mInstatnce;
    private TravelerManager(){}

    public static TravelerManager getInstatnce(){
        if(mInstatnce==null){
            synchronized (TravelerManager.class){
                if(mInstatnce==null){
                    mInstatnce = new TravelerManager();
                }
            }
        }
        return mInstatnce;
    }


    public List<InfoDaoMeizi> loadDB(){
        QueryBuilder tQuery = new QueryBuilder(InfoDaoHome.class);
        tQuery.appendOrderDescBy("createTime");
        tQuery.limit(0, Apis.PAGESIZE);
        return DaoManager.getInstance().mOrm.query(tQuery);


    }

    public Subscription loadTravelerList(int pPage){
        return ApiManager.getInstance().mApis.loadTravelerList(pPage + "", "", "", "")
                .subscribeOn(Schedulers.io())
//                .doOnNext(new Action1<InfoLocalListRsp>() {
//                    @Override
//                    public void call(InfoLocalListRsp infoLocalListRsp) {
//                        if (infoLocalListRsp != null && infoLocalListRsp.results != null) {
//                            for (InfoLocal tInfo : infoLocalListRsp.results.path) {
//                                DaoManager.getInstance().mOrm.save(tInfo);
//                            }
//                        }
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<InfoTravelerListRsp>() {
                    @Override
                    public void call(InfoTravelerListRsp infoTravelerListRsp) {
                        if (infoTravelerListRsp == null || infoTravelerListRsp.results == null) {
                            EventBus.post(new EventTravelerList(false, "null"));
                        } else if (infoTravelerListRsp.code != InfoUserRsp.CODE_SUC) {
                            EventBus.post(new EventTravelerList(false, infoTravelerListRsp.remark));
                        } else {
                            EventBus.post(new EventTravelerList(infoTravelerListRsp.results.demands));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        EventBus.post(new EventTravelerList(false, "error"));
                    }
                });
    }


    private boolean isTheSameDay(Date one, Date another) {
        Calendar _one = Calendar.getInstance();
        _one.setTime(one);
        Calendar _another = Calendar.getInstance();
        _another.setTime(another);
        int oneDay = _one.get(Calendar.DAY_OF_YEAR);
        int anotherDay = _another.get(Calendar.DAY_OF_YEAR);

        return oneDay == anotherDay;
    }
}
