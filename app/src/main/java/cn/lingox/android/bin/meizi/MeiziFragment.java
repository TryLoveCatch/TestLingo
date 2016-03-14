package cn.lingox.android.bin.meizi;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import cn.lingox.android.R;
import cn.lingox.android.bin.meizi.view.MeiziHView;
import cn.lingox.android.bin.net.Apis;
import cn.lingox.android.bin.photo.PhotoFragment;
import cn.lingox.android.dao.InfoDaoMeizi;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.event.EventMeizi;
import cn.lingox.android.share.event.EventMeiziClicked;
import cn.lingox.android.share.fragment.RecylerFragment;
import cn.lingox.android.share.fragment.SharedFragmentActivity;
import cn.lingox.android.util.Constant;

public class MeiziFragment extends RecylerFragment{
    
    //===============界面变量==============
    //===============逻辑变量==============
    //===============生命周期==============
    //===============事件接口==============
    @Override
    protected List<? extends InfoBase> loadDB(){
        return MeiziManager.getInstatnce().loadDB(Apis.type_meizi);
    }

    @Override
    protected void loadNet() {
        addSubscription(MeiziManager.getInstatnce().loadHomeData(mPage));
    }

    @Override
    protected void setHolderView() {
        mAdp.setHolderViews(MeiziHView.class);
    }

    @Override
    protected int getItemSpace() {
        return getResources().getDimensionPixelSize(R.dimen.dp_3);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager tLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        tLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return tLayoutManager;
    }


    public void onEvent(EventMeizi pData){
        if(pData.data!=null){
            loadSuc(pData.data);
        }else{
            loadFail();
        }
    }

    public void onEvent(EventMeiziClicked pData){
        startPictureActivity(pData.position, pData.view);
    }
    //===============对外方法==============
    //===============私有方法==============
    private void startPictureActivity(int pPosition, View pView) {
        InfoDaoMeizi tInfo = (InfoDaoMeizi)mArrData.get(pPosition);
//        Intent tIntent = new Intent(getActivity(), PhotoViewPagerActivity.class);
//        tIntent.putExtra(Constant.EXTRA_POSITION, pPosition);
////        tIntent.putExtra(Constant.EXTRA_COUNT, mArrData.size());
//        tIntent.putExtra(Constant.EXTRA_URL, tInfo.url);
//
//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pView,
//                        getActivity().getString(R.string.transition_share_photo));
//        try {
//            ActivityCompat.startActivity(getActivity(), tIntent, optionsCompat.toBundle());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            getActivity().startActivity(tIntent);
//        }

        Bundle tData = new Bundle();
        tData.putString(Constant.EXTRA_URL, tInfo.url);
        SharedFragmentActivity.startFragmentActivity(getActivity(), PhotoFragment.class, tData, pView);
    }
}
