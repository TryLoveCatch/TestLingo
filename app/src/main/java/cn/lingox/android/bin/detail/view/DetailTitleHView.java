package cn.lingox.android.bin.detail.view;

import android.content.Context;
import android.widget.TextView;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.bin.detail.InfoDetailTitle;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.view.BaseHolderView;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class DetailTitleHView extends BaseHolderView{
    @Bind(R.id.detail_title_item_title)
    TextView mTxt;

    public DetailTitleHView(Context pContext){
        super(pContext, R.layout.detail_title_item);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoDetailTitle tInfo = (InfoDetailTitle)pInfo;
        mTxt.setText(tInfo.title);
    }

}
