package cn.lingox.android.bin.text.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.dao.InfoDaoMeizi;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.framework.view.BaseHolderView;
import cn.lingox.android.share.event.EventTextClicked;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class TextHView extends BaseHolderView{
    @Bind(R.id.text_item_txt_name)
    TextView mTxtName;
    @Bind(R.id.text_item_txt_time)
    TextView mTxtTime;
    @Bind(R.id.text_item_txt_desc)
    TextView mTxtDesc;

    private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd");

    public TextHView(Context pContext){
        super(pContext, R.layout.text_item);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoDaoMeizi tInfo = (InfoDaoMeizi)pInfo;

        mTxtName.setText(tInfo.who);
        mTxtTime.setText(mSdf.format(tInfo.publishedAt));
        mTxtDesc.setText(tInfo.desc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.post(new EventTextClicked(tInfo.url));
            }
        });
    }

}
