package cn.lingox.android.share.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import cn.lingox.android.framework.BaseFragment;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.share.adapter.TabAdapter;
import cn.lingox.android.test.R;

public abstract class TabFragment extends BaseFragment{
    
    //===============界面变量==============
    @Bind(R.id.tab_tab)
    protected TabLayout mTabLayout;
    @Bind(R.id.tab_viewpager)
    protected ViewPager mViewPager;
    protected TabAdapter mTabAdapter;
    //===============逻辑变量==============
    //===============生命周期==============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.activity_main);
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
        mTabAdapter = new TabAdapter(getFragmentManager());
        mViewPager.setAdapter(mTabAdapter);

    }

    @Override
    public void initData() {
        loadData();
    }
    //===============对外方法==============
    protected abstract void loadData();
    protected abstract void setTabView(TabFragment.TabView tabView, int postion);

    protected void loadSuc(ArrayList<Fragment> pFragmentList){
        mTabAdapter.setData(pFragmentList);

        fillView();
    }

    protected void loadFail(){

    }
    //===============私有方法==============

    private void fillView(){
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i=0;i<mTabLayout.getTabCount();i++){
            TabView tView = new TabView(getContext());
            setTabView(tView, i);
            mTabLayout.getTabAt(i).setCustomView(tView);
        }
    }

    protected class TabView extends View{

        public TextView mTxtTitle;
        public TextView mTxtNumber;
        public ImageView mImgIcon;

        public TabView(Context context) {
            super(context);
            initView(context);
        }

        private void initView(Context context) {
            LayoutInflater tInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = tInflater.inflate(R.layout.tab_item, null);
            mTxtTitle = (TextView) view.findViewById(R.id.tab_item_txt_title);
            mTxtNumber = (TextView) view.findViewById(R.id.tab_item_txt_number);
            mImgIcon = (ImageView) view.findViewById(R.id.tab_item_img);
        }
    }
}
