package cn.lingox.android.framework.view;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.test.R;

/**
 * Created by lipeng21 on 2016/4/13.
 */
public class FooterViewHolder extends BaseHolderView{

    @Bind(R.id.log_footer_txt)
    protected TextView mTxt;
    @Bind(R.id.log_footer_prg)
    protected ProgressBar mPrg;

    public FooterViewHolder(Context pContext) {
        super(pContext, R.layout.list_footer);
    }


    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {

    }
    public void bindData(boolean tIsHasMore) {
        if(tIsHasMore){
            mPrg.setVisibility(View.VISIBLE);
            mTxt.setText(R.string.progress_loading);
        }else{
            mPrg.setVisibility(View.GONE);
            mTxt.setText(R.string.no_more_data);
        }
    }
}
