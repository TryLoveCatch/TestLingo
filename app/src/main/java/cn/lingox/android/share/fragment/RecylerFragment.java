package cn.lingox.android.share.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.bin.api.Apis;
import cn.lingox.android.framework.BaseFragment;
import cn.lingox.android.framework.IToolbarAndFab;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.framework.view.BaseAdapter;
import cn.lingox.android.util.UtilManager;

public abstract class RecylerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IToolbarAndFab {

    //===============界面变量==============
    @Bind(R.id.recyler_swp)
    SwipeRefreshLayout mSwp;
    @Bind(R.id.recyler_rcl)
    RecyclerView mRcl;
    protected BaseAdapter mAdp;
    protected RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.OnScrollListener mScrollListener;
    //===============逻辑变量==============
    protected List<InfoBase> mArrData;
    protected int mPage;

    private boolean mIsResumed;
    private boolean mIsAll;//已经没有更多了
    private boolean mIsLoadMore;
    private boolean mIsRefresh;
    private boolean mIsSmooth;
    private boolean mIsTab;//是否是tab的
    //===============生命周期==============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.recyler);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!mIsTab){
            resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!mIsTab){
            pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        clear();
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(mIsTab){
                resume();
            }
        }else{
            if(mIsTab){
                pause();
            }
        }
    }

    //===============事件接口==============
    @Override
    public void initViewProperty() {
        mSwp.setOnRefreshListener(this);
        mSwp.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);

        mLayoutManager = getLayoutManager();
        mRcl.setLayoutManager(mLayoutManager);
        mAdp = new BaseAdapter(getActivity());
        mAdp.setData(mArrData);
        setHolderView();
        MyItemDecoration tItemDec = new MyItemDecoration();
        tItemDec.setSpace(getItemSpace());
        mRcl.addItemDecoration(tItemDec);
        mRcl.setAdapter(mAdp);
//        mRcl.setItemAnimator(new DefaultItemAnimator());
        mScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int tLastVisibleItem = 0;

                if(mLayoutManager instanceof  StaggeredGridLayoutManager){
                    int[] tArr = ((StaggeredGridLayoutManager)mLayoutManager)
                            .findLastCompletelyVisibleItemPositions(null);
                    tLastVisibleItem = findMax(tArr);
                }else if(mLayoutManager instanceof LinearLayoutManager){
                    tLastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastCompletelyVisibleItemPosition();
                }

                int tItemCount = mAdp.getItemCount();
//                L.e("tLastVisibleItem," + tLastVisibleItem + " tItemCount," + tItemCount + " mPage," + mPage + " mIsAll," + mIsAll);

                if(!mIsLoadMore && !mIsRefresh && !mSwp.isRefreshing() && !mIsAll && tLastVisibleItem >= tItemCount - 1) {
                    mIsLoadMore = true;
                    mPage++;
                    loadData();
                }

                //滚动
                //解决smoothScrollToPosition 不能滚到头的问题
                if(mIsSmooth){
                    int tFirstVisibleItem = 0;
                    if(mLayoutManager instanceof  StaggeredGridLayoutManager){
                        int[] tArr = ((StaggeredGridLayoutManager)mLayoutManager)
                                .findFirstCompletelyVisibleItemPositions(null);
                        tFirstVisibleItem = findMin(tArr);
                    }else if(mLayoutManager instanceof LinearLayoutManager){
                        tFirstVisibleItem = ((LinearLayoutManager)mLayoutManager).findFirstCompletelyVisibleItemPosition();
                    }

                    if(tFirstVisibleItem == 0){
                        mIsSmooth = false;
                        mAdp.notifyDataSetChanged();
                    }else{
                        mRcl.smoothScrollToPosition(0);
                    }
                }
            }
        };
        mRcl.addOnScrollListener(mScrollListener);

        mEmptyView.setOnRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPage = 1;
                loadData();
            }
        });

    }

    @Override
    public void initData() {
        mArrData = new ArrayList<>();
        mPage = 1;
        mIsAll = false;
        mIsLoadMore = false;
        mIsSmooth = false;
        mIsRefresh = false;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onToolbarClicked(Toolbar pToolbar) {
        if(mIsLoadMore || mSwp.isRefreshing()) return;
        mIsSmooth = true;
        mRcl.smoothScrollToPosition(0);
    }

    @Override
    public void onFabClicked(FloatingActionButton pFab) {
        mRcl.scrollToPosition(0);
        setRefreshing(true, 10);
        refresh();
    }
    //===============对外方法==============

    protected abstract List<? extends InfoBase> loadDB();
    protected abstract void loadNet();
    protected abstract void setHolderView();
    protected abstract int getItemSpace();
    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected void loadSuc(List<? extends InfoBase> pArrInfos){
        if (mArrData.size() == 0 && pArrInfos.size() == 0) {
            showEmptyView(mSwp, TYPE_NO_DATA);
            return;
        }
        fillView(pArrInfos);
    }

    protected void loadFail(){
        if (mArrData.size() > 0) {
            showToast(R.string.msg_top_fail);
        } else {
            setRefreshing(false, 10);
            showEmptyView(mSwp, TYPE_ERROR);
        }
    }

    //===============私有方法==============
    private void loadData(){

        if(!UtilManager.getInstance().mUtilNet.isNetAvailable()){
            if(isListValid(mArrData)){
                showToast(R.string.msg_top_nonet);
            }else {
                showEmptyView(mSwp, TYPE_NO_NET);
            }
            setRefreshing(false, 1000);
            return;
        }

        loadNet();
    }

    private void fillView(List<? extends InfoBase> pArrInfos){
        if(mEmptyView.getVisibility()==View.VISIBLE) {
            hideEmptyView(mSwp);
        }

        if (pArrInfos.size() < Apis.PAGESIZE) {
            mIsAll = true;
            mAdp.setIsHasMore(false);
        }

        if(mIsRefresh){
            mArrData.clear();
        }

        mArrData.addAll(pArrInfos);
        mAdp.notifyDataSetChanged();

        mIsLoadMore = false;
        mIsRefresh = false;

        // 防止刷新消失太快
        setRefreshing(false, 1000);
    }

    private void refresh(){
        if(!mIsLoadMore){
            mIsRefresh = true;
            mPage = 1;
            loadData();
        }
    }

    private void clear(){
        mRcl.removeOnScrollListener(mScrollListener);
    }

    private int findMax(int[] pArr){
        int tResult = pArr[pArr.length - 1];
        for(int i : pArr){
            if(tResult < i){
                tResult = i;
            }
        }
        return tResult;
    }

    private int findMin(int[] pArr){
        int tResult = pArr[0];
        for(int i : pArr){
            if(tResult > i){
                tResult = i;
            }
        }
        return tResult;
    }

    //解决SwipeRefreshLayout setRefreshing()不管用的问题
    private void setRefreshing(final boolean pRefreshing, final long pDelayMillis){
        mSwp.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwp.setRefreshing(pRefreshing);
            }
        }, pDelayMillis);
    }

    private void resume(){
        try {
            EventBus.register(this);
        }catch (Exception e){}

        if(!mIsResumed) {
            mIsResumed = true;
            //加载db缓存
            List<? extends InfoBase> tArr = loadDB();
            if(isListValid(tArr)) {
                mArrData.addAll(tArr);
                mAdp.notifyDataSetChanged();
            }

            if(isListValid(mArrData)) {
                setRefreshing(true, 300);
                mRcl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 1000);
            }else{
                showEmptyView(mSwp, TYPE_LOADING);
                refresh();
            }

        }
    }

    private void pause(){
        try {
            EventBus.unregister(this);
        }catch (Exception e){}
    }


    public class MyItemDecoration extends RecyclerView.ItemDecoration{

        private int space;
        public void setSpace(int space){
            this.space = space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if(space > 0 ){
                if (parent.getLayoutManager() instanceof LinearLayoutManager) {
                    if(parent.getChildLayoutPosition(view) != 0) {
                        outRect.top = space;
                    }
                } else {
                    outRect.left = space;
                    if(parent.getChildLayoutPosition(view) != 0) {
                        outRect.top = space;
                    }
                }
            }
        }
    }
}
