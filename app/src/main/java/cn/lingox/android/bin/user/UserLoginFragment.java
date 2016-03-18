package cn.lingox.android.bin.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import butterknife.Bind;
import cn.lingox.android.MainActivity;
import cn.lingox.android.framework.BaseFragment;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.event.EventUserLogin;
import cn.lingox.android.test.R;
import cn.lingox.android.util.UtilString;

public class UserLoginFragment extends BaseFragment implements View.OnClickListener {
    
    //===============界面变量==============
    @Bind(R.id.user_login_til_name)
    TextInputLayout mTilName;
    @Bind(R.id.user_login_til_password)
    TextInputLayout mTilPassword;
    @Bind(R.id.user_login_btn)
    Button mBtnLogin;

    private Animation mAnimShake;
    //===============逻辑变量==============
    //===============生命周期==============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.user_login);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        try{
            EventBus.register(this);
        }catch(Exception e){}
    }
    
    @Override
    public void onPause() {
        super.onPause();
        try{
            EventBus.unregister(this);
        }catch(Exception e){}
    }
    
    @Override
    public void onStop() {
        super.onStop();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    //===============事件接口==============
    @Override
    public void initViewProperty() {
        setTitle(R.string.app_login);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mAnimShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        loadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_login_btn:
                login();
                break;
        }
    }

    public void onEvent(EventUserLogin pData){
        hideProgress();
        if(pData.data!=null){
            showToast("Login success");
            Intent tIntent = new Intent(getContext(), MainActivity.class);
            startActivity(tIntent);
        }else{
            if(UtilString.isNotBlank(pData.resultMsg)){
                showToast(pData.resultMsg);
            }else {
                showToast("Login failed");
            }
        }
    }
    //===============对外方法==============
    //===============私有方法==============
    private void loadData(){
        
    }
    
    private void fillView(){
        
    }

    private boolean checked(){
        mTilName.setErrorEnabled(false);
        mTilPassword.setErrorEnabled(false);

        boolean tResult = true;

        if(mTilName.getEditText().getText()==null || UtilString.isBlank(mTilName.getEditText().getText().toString())){
            tResult = false;
            mTilName.setErrorEnabled(true);
            mTilName.setError(getString(R.string.user_login_name_error_null));
            shake(mTilName);
        }else if(mTilPassword.getEditText().getText()==null || UtilString.isBlank(mTilPassword.getEditText().getText().toString())){
            tResult = false;
            mTilPassword.setErrorEnabled(true);
            mTilPassword.setError(getString(R.string.user_login_password_error_null));
            shake(mTilPassword);
        }

        return tResult;
    }

    private void login(){
        if(checked()){
            showProgress(getString(R.string.progress_msg_login));
            UserManager.getInstatnce().login(mTilName.getEditText().getText().toString()
                    , mTilPassword.getEditText().getText().toString());
        }
    }

    private void shake(View pView){
        pView.startAnimation(mAnimShake);
    }
}
