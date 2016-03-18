package cn.lingox.android.bin.home.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.dao.InfoDaoHome;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.framework.view.BaseHolderView;
import cn.lingox.android.share.event.EventHomeClicked;
import cn.lingox.android.util.UtilManager;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class HomeHView extends BaseHolderView{
    @Bind(R.id.home_item_txt_name)
    TextView mTxtName;
    @Bind(R.id.home_item_txt_time)
    TextView mTxtTime;
    @Bind(R.id.home_item_txt_desc)
    TextView mTxtDesc;
    @Bind(R.id.home_item_img)
    ImageView mImg;

    private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd");

    public HomeHView(Context pContext){
        super(pContext, R.layout.home_item);

//        ViewGroup.LayoutParams tParams = mImg.getLayoutParams();
//        tParams.height = Math.round(tParams.width * 1.0f * 4 / 3);
//        mImg.setLayoutParams(tParams);

        ViewGroup.LayoutParams tParams = mImg.getLayoutParams();
        tParams.width = UtilManager.getInstance().mUtilPhone.getScreenWidth();
        tParams.height = Math.round(tParams.width * 1.0f * 4 / 3);
        mImg.setLayoutParams(tParams);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoDaoHome tInfo = (InfoDaoHome)pInfo;

        mTxtName.setText(tInfo.name);
        mTxtTime.setText(mSdf.format(tInfo.createTime));
        mTxtDesc.setText(tInfo.desc);

        Picasso.with(mContext)
                .load(tInfo.picUrl)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(mImg);

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.post(new EventHomeClicked(pPosition, mImg));
            }
        });
    }

}
