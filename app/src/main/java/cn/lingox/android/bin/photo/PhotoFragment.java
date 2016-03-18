package cn.lingox.android.bin.photo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.framework.BaseActivity;
import cn.lingox.android.framework.BaseFragment;
import cn.lingox.android.share.view.pinchImageView.PinchImageView;
import cn.lingox.android.util.Constant;
import cn.lingox.android.util.UtilString;

public class PhotoFragment extends BaseFragment{
    
    //===============界面变量==============
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.photo_img)
    PinchImageView mImg;
    //===============逻辑变量==============
    private String mUrl;
    //===============生命周期==============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.photo);
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    @Override
    public void onPause() {
        super.onPause();
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
        ((BaseActivity)getActivity()).setSupportActionBar(mToolbar);

        Picasso.with(getActivity())
                .load(mUrl)
                .into(mImg);
    }

    @Override
    public void initData() {
        Bundle tData = getArguments();
        if(tData!=null){
            mUrl = tData.getString(Constant.EXTRA_URL);
        }

        if(UtilString.isBlank(mUrl)){
            getActivity().finish();
        }
    }
    //===============对外方法==============
    //===============私有方法==============
}
