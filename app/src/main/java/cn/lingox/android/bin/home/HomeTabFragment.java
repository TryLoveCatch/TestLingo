package cn.lingox.android.bin.home;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import cn.lingox.android.bin.local.LocalFragment;
import cn.lingox.android.share.fragment.TabFragment;
import cn.lingox.android.test.R;

public class HomeTabFragment extends TabFragment{
    //===============界面变量==============
    private LocalFragment mFrgLocal;
    private HomeFragment mFrgHome2;
    //===============逻辑变量==============
    //===============生命周期==============
    
    //===============事件接口==============
    @Override
    protected ArrayList<Fragment> beforeLoadData() {
        ArrayList<Fragment> tListFrgs = new ArrayList<>();
        mFrgLocal = new LocalFragment();
        mFrgHome2 = new HomeFragment();
        tListFrgs.add(mFrgLocal);
        tListFrgs.add(mFrgHome2);
        return tListFrgs;
    }

    @Override
    protected void setTabView(TabView tabView, int postion) {
        switch (postion){
            case 0:
                tabView.setTitle(R.string.home_tab_local);
                break;
            case 1:
                tabView.setTitle(R.string.home_tab_traveler);
                break;
        }
    }
    //===============对外方法==============
    //===============私有方法==============
}
