package cn.lingox.android.bin.user;

import java.util.Iterator;
import java.util.Map;

import cn.lingox.android.bin.api.ApiManager;
import cn.lingox.android.bin.user.info.InfoUser;
import cn.lingox.android.bin.user.info.InfoUserRsp;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.event.EventUserLogin;
import cn.lingox.android.util.UtilManager;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class UserManager {

    //===============逻辑变量==============
    private static UserManager mInstatnce;
    private InfoUser mInfoUser;

    private UserManager(){}


    //===============对外方法==============
    public static UserManager getInstatnce(){
        if(mInstatnce==null){
            synchronized (UserManager.class){
                if(mInstatnce==null){
                    mInstatnce = new UserManager();
                }
            }
        }
        return mInstatnce;
    }
    public Subscription login(final String pUserNameOrEmail, final String pPassword){
//        Map<String, String> params = new HashMap<>();
//        params.put(emailOrUsernameStr, pUserNameOrEmail);
//        params.put(passwordStr, pPassword);
//        String tStr = getParamString(params);
        return ApiManager.getInstance().mApis.login(pUserNameOrEmail, pPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<InfoUserRsp>() {
                    @Override
                    public void call(InfoUserRsp infoUserRsp) {
                        if(infoUserRsp==null){
                            EventBus.post(new EventUserLogin(false, "null"));
                        }else if(infoUserRsp.code != InfoUserRsp.CODE_SUC){
                            EventBus.post(new EventUserLogin(false, infoUserRsp.remark));
                        }else {
                            mInfoUser = infoUserRsp.results;
                            UtilManager.getInstance().mUtilSharedP.setUserName(pUserNameOrEmail);
                            UtilManager.getInstance().mUtilSharedP.setUserPassword(pPassword);
                            EventBus.post(new EventUserLogin(mInfoUser));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        EventBus.post(new EventUserLogin(false, "error"));
                    }
                });

    }

    public InfoUser getInfoUser(){
        return mInfoUser;
    }
    //===============私有方法==============
    private String getParamString(Map<String, String> params) {
        if(params.size() <= 0) return null;

        StringBuilder sb = new StringBuilder();
        Iterator tIter = params.entrySet().iterator();
        while (tIter.hasNext()){
            Map.Entry<String, String> tEntry = (Map.Entry)tIter.next();
            sb.append(tEntry.getKey()).append("=").append(tEntry.getValue()).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
