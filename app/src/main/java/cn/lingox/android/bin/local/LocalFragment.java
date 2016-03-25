package cn.lingox.android.bin.local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cn.lingox.android.bin.detail.DetailActivity;
import cn.lingox.android.bin.local.view.LocalHView;
import cn.lingox.android.dao.InfoDaoHome;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.event.EventLocalClicked;
import cn.lingox.android.share.event.EventLocalList;
import cn.lingox.android.share.fragment.RecylerFragment;
import cn.lingox.android.test.R;
import cn.lingox.android.util.Constant;

public class LocalFragment extends RecylerFragment{
    
    //===============界面变量==============
    //===============逻辑变量==============
    //===============生命周期==============
    //===============事件接口==============
    @Override
    protected List<? extends InfoBase> loadDB(){
        return null;
    }

    @Override
    protected void loadNet() {
        addSubscription(LocalManager.getInstatnce().loadLocalList(mPage));
    }

    @Override
    protected void setHolderView() {
        mAdp.setHolderViews(LocalHView.class);
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

    public void onEvent(EventLocalList pData){
        if(pData.data!=null){
            loadSuc(pData.data);
        }else{
            loadFail();
        }
    }

    public void onEvent(EventLocalClicked pData){
//        startPictureActivity(pData.position, pData.view);
    }
    //===============对外方法==============
    //===============私有方法==============
    private void startPictureActivity(int pPosition, View pView) {
        InfoDaoHome tInfo = (InfoDaoHome)mArrData.get(pPosition);

        Bundle tData = new Bundle();
        tData.putString(Constant.EXTRA_URL, tInfo.picUrl);
        tData.putString(Constant.EXTRA_NAME, tInfo.desc);
        tData.putSerializable(Constant.EXTRA_INFO, tInfo.createTime);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtras(tData);

        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pView,
                        getActivity().getString(R.string.transition_share_photo));
        try {
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            getActivity().startActivity(intent);
        }
    }
}
