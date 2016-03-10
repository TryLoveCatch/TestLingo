package cn.lingox.android.share.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import cn.lingox.android.framework.BaseFragment;

/**
 * Created by lipeng21 on 2015/11/12.
 */
public abstract class MainInnerFragment extends BaseFragment{

    public abstract void onToolbarClicked(Toolbar pToolbar);
    public abstract void onFabClicked(FloatingActionButton pFab);
}
