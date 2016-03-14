package cn.lingox.android.bin.text;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.ArrayList;
import java.util.List;

import cn.lingox.android.bin.meizi.info.InfoMeiziData;
import cn.lingox.android.bin.api.ApiManager;
import cn.lingox.android.bin.api.Apis;
import cn.lingox.android.dao.DaoManager;
import cn.lingox.android.dao.InfoDaoMeizi;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.event.EventText;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class TextManager {

    private static TextManager mInstatnce;
    private TextManager(){}

    public static TextManager getInstatnce(){
        if(mInstatnce==null){
            synchronized (TextManager.class){
                if(mInstatnce==null){
                    mInstatnce = new TextManager();
                }
            }
        }
        return mInstatnce;
    }


    public List<InfoDaoMeizi> loadDB(String pType){
        QueryBuilder tQuery = new QueryBuilder(InfoDaoMeizi.class);
        tQuery.where("type like ?", new String[]{pType + "%"});
        tQuery.appendOrderDescBy("updatedAt");
        tQuery.limit(0, Apis.PAGESIZE);
        return DaoManager.getInstance().mOrm.query(tQuery);


    }

    public Subscription loadHomeData(int pPage, String pType){
        return loadHomeData(pPage, Apis.PAGESIZE, pType);
    }

    public Subscription loadHomeData(int pPage, int pPageSize, final String pType){
        return ApiManager.getInstance().mApis.getDataByType(pPage, pPageSize, pType)
                .subscribeOn(Schedulers.io())
                .map(new Func1<InfoMeiziData, ArrayList<InfoDaoMeizi>>() {
                    @Override
                    public ArrayList<InfoDaoMeizi> call(InfoMeiziData infoMeiziData) {
                        return infoMeiziData.results;
                    }
                })
                .doOnNext(new Action1<ArrayList<InfoDaoMeizi>>() {
                    @Override
                    public void call(ArrayList<InfoDaoMeizi> infoDaoMeizis) {
                        for (InfoDaoMeizi tInfo : infoDaoMeizis) {
                            if (DaoManager.getInstance().mOrm.queryById(tInfo.objectId, InfoDaoMeizi.class) == null) {
                                DaoManager.getInstance().mOrm.insert(tInfo, ConflictAlgorithm.Replace);
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<InfoDaoMeizi>>() {
                    @Override
                    public void call(ArrayList<InfoDaoMeizi> infoGanks) {
                        if(infoGanks==null || infoGanks.size() <= 0){
                            EventBus.post(new EventText(false, "数据为空"));
                        }else {
                            EventBus.post(new EventText(infoGanks));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        EventBus.post(new EventText(false, ""));
                    }
                });
    }
}
