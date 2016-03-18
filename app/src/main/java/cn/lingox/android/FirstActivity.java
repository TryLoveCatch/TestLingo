package cn.lingox.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import cn.lingox.android.bin.guide.GuideActivity;
import cn.lingox.android.bin.user.UserLoginFragment;
import cn.lingox.android.bin.user.UserManager;
import cn.lingox.android.framework.BaseActivity;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.event.EventUserLogin;
import cn.lingox.android.share.fragment.SharedFragmentActivity;
import cn.lingox.android.test.R;
import cn.lingox.android.util.UtilManager;
import cn.lingox.android.util.UtilString;

public class FirstActivity extends BaseActivity{
    
    //===============界面变量==============
    private Handler mHandler;
    //===============逻辑变量==============
    //===============生命周期==============
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setForbidStartActivityAnimation(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState, R.layout.first);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        try{
            EventBus.register(this);
        }catch(Exception e){}
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        try{
            EventBus.unregister(this);
        }catch(Exception e){}
    }
    
    //===============事件接口==============
    @Override
    public void initViewProperty() {
        
    }

    @Override
    public void initData() {
        mHandler = new Handler();
    }

    public void onEvent(EventUserLogin pData){
        if(pData.data!=null){
            startMainActivity();
        }else{
            if(UtilString.isNotBlank(pData.resultMsg)){
                showToast(pData.resultMsg);
            }else {
                showToast("Login failed");
            }
            startGuideActivity();
        }
    }
    //===============对外方法==============
    //===============私有方法==============
    private void loadData(){
        String tName = UtilManager.getInstance().mUtilSharedP.getUserName();
        String tPassword = UtilManager.getInstance().mUtilSharedP.getUserPassword();
        //这里需要判断登录
        if(UtilString.isNotBlank(tName) && UtilString.isNotBlank(tPassword)){
            UserManager.getInstatnce().login(tName
                    , tPassword);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startGuideActivity();
                }
            }, 1500);
        }
    }

    private void startGuideActivity(){
        Intent tIntent = new Intent(FirstActivity.this, GuideActivity.class);
        startActivity(tIntent);
        finish();
    }

    private void startLoginActivity(){
        SharedFragmentActivity.startFragmentActivity(this, UserLoginFragment.class, true);
        finish();
    }

    private void startMainActivity(){
        Intent tIntent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(tIntent);
        finish();
    }
}
