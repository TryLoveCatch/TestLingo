package cn.lingox.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import cn.lingox.android.bin.guide.GuideActivity;
import cn.lingox.android.framework.BaseActivity;

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
        loadData();
    }
    
    //===============事件接口==============
    @Override
    public void initViewProperty() {
        
    }

    @Override
    public void initData() {

        mHandler = new Handler();
    }
    //===============对外方法==============
    //===============私有方法==============
    private void loadData(){
        //这里需要判断登录
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tIntent = new Intent(FirstActivity.this, GuideActivity.class);
                startActivity(tIntent);
                finish();
            }
        }, 1500);
//        mHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent tIntent = new Intent(FirstActivity.this, MainActivity.class);
//                startActivity(tIntent);
//                finish();
//            }
//        }, 3000);
    }
}
