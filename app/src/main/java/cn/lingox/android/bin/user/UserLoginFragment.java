package cn.lingox.android.bin.user;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import cn.lingox.android.R;
import cn.lingox.android.framework.BaseFragment;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.util.UtilString;

public class UserLoginFragment extends BaseFragment{
    
    //===============界面变量==============
    @Bind(R.id.user_login_til_name)
    TextInputLayout mTilName;
    @Bind(R.id.user_login_til_password)
    TextInputLayout mTilPassword;
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

    }

    @Override
    public void initData() {
        loadData();
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
            tResult = true;
            mTilName.setErrorEnabled(true);
            mTilName.setError(getString(R.string.user_login_name_error_null));
        }else if(mTilPassword.getEditText().getText()==null || UtilString.isBlank(mTilPassword.getEditText().getText().toString())){
            tResult = true;
            mTilPassword.setErrorEnabled(true);
            mTilPassword.setError(getString(R.string.user_login_password_error_null));
        }

        return tResult;
    }
}
