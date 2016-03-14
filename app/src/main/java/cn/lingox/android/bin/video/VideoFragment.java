package cn.lingox.android.bin.video;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.lingox.android.R;
import cn.lingox.android.bin.video.view.VideoHView;
import cn.lingox.android.dao.InfoDaoVideo;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.event.EventVideo;
import cn.lingox.android.share.event.EventVideoClicked;
import cn.lingox.android.share.fragment.RecylerFragment;
import cn.lingox.android.share.fragment.SharedFragmentActivity;
import cn.lingox.android.share.fragment.WebPlayFragment;
import cn.lingox.android.util.Constant;

public class VideoFragment extends RecylerFragment{
    
    //===============界面变量==============
    //===============逻辑变量==============
    private String mType;
    //===============生命周期==============


    @Override
    public void initData() {
        super.initData();
        mType = getArguments().getString(Constant.EXTRA_TYPE);
    }

    //===============事件接口==============
    @Override
    protected List<? extends InfoBase> loadDB(){
        return VideoManager.getInstatnce().loadDB();
    }

    @Override
    protected void loadNet() {
        addSubscription(VideoManager.getInstatnce().loadHomeData(mPage, mType));
    }

    @Override
    protected void setHolderView() {
        mAdp.setHolderViews(VideoHView.class);
    }


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager tLayoutManager = new LinearLayoutManager(getActivity());
        return tLayoutManager;
    }

    @Override
    protected int getItemSpace() {
        return getResources().getDimensionPixelSize(R.dimen.dp_12);
    }

    public void onEvent(EventVideo pData){
        if(pData.data!=null){
            loadSuc(pData.data);
        }else{
            loadFail();
        }
    }

    public void onEvent(EventVideoClicked pData){
        startVideoActivity(pData.position);
    }

    //===============对外方法==============
    //===============私有方法==============
    private void startVideoActivity(int pPosition) {
        InfoDaoVideo tInfo = (InfoDaoVideo)mArrData.get(pPosition);

        Bundle tData = new Bundle();
        tData.putString(Constant.EXTRA_URL, tInfo.videoUrl);
        SharedFragmentActivity.startFragmentActivity(getActivity(), WebPlayFragment.class, tData);
    }
}
