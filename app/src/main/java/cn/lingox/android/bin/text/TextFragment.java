package cn.lingox.android.bin.text;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.lingox.android.test.R;
import cn.lingox.android.bin.text.view.TextHView;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.event.EventText;
import cn.lingox.android.share.event.EventTextClicked;
import cn.lingox.android.share.fragment.RecylerFragment;
import cn.lingox.android.share.fragment.WebBaseActivity;
import cn.lingox.android.util.Constant;

public class TextFragment extends RecylerFragment{
    
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
        return TextManager.getInstatnce().loadDB(mType);
    }

    @Override
    protected void loadNet() {
        addSubscription(TextManager.getInstatnce().loadHomeData(mPage, mType));
    }

    @Override
    protected void setHolderView() {
        mAdp.setHolderViews(TextHView.class);
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

    public void onEvent(EventText pData){
        if(pData.data!=null){
            loadSuc(pData.data);
        }else{
            loadFail();
        }
    }

    public void onEvent(EventTextClicked pData){
        startTxtActivity(pData.url);
    }

    //===============对外方法==============
    //===============私有方法==============
    private void startTxtActivity(String pUrl) {
        Bundle tData = new Bundle();
        tData.putString(Constant.EXTRA_URL, pUrl);
        Intent tIntent = new Intent(getActivity(), WebBaseActivity.class);
        tIntent.putExtras(tData);
        getActivity().startActivity(tIntent);
    }
}
