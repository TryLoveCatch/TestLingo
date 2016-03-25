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
import android.widget.LinearLayout;
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
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.tab);
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
        loadData();
    }

    @Override
    public void initData() {

    }
    //===============对外方法==============
    protected abstract ArrayList<Fragment> beforeLoadData();
    protected abstract void setTabView(TabFragment.TabView tabView, int postion);


    protected void loadData(){
        ArrayList<Fragment> tFragmentList = beforeLoadData();

        if(!isListValid(tFragmentList)){
            return;
        }
        fillView(tFragmentList);
    }
    //===============私有方法==============

    private void fillView(ArrayList<Fragment> pFragmentList){
        mTabAdapter = new TabAdapter(getFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mTabAdapter.setData(pFragmentList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i=0;i<mTabLayout.getTabCount();i++){
            TabView tView = new TabView(getContext());
            setTabView(tView, i);
            mTabLayout.getTabAt(i).setCustomView(tView);
        }
    }

    protected class TabView extends LinearLayout{

        private TextView mTxtTitle;
        private TextView mTxtNumber;
        private ImageView mImgIcon;

        public TabView(Context context) {
            super(context);
            initView(context);
        }

        private void initView(Context context) {
            LayoutInflater tInflater = LayoutInflater.from(context);
            View view = tInflater.inflate(R.layout.tab_item, this);
            mTxtTitle = (TextView) view.findViewById(R.id.tab_item_txt_title);
            mTxtNumber = (TextView) view.findViewById(R.id.tab_item_txt_number);
            mImgIcon = (ImageView) view.findViewById(R.id.tab_item_img);
        }

        public void setTitle(String tTitle){
            mTxtTitle.setText(tTitle);
        }

        public void setTitle(int tId){
            mTxtTitle.setText(tId);
        }

        public void setIcon(int tId){
            mImgIcon.setImageResource(tId);
        }

        public void setNumber(String tNumber){
            mTxtNumber.setVisibility(View.VISIBLE);
            mTxtNumber.setText(tNumber);
        }

        public void setNumber(int tId){
            mTxtNumber.setVisibility(View.VISIBLE);
            mTxtNumber.setText(tId);
        }
    }
}
