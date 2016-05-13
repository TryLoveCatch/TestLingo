package cn.lingox.android.bin.traveler.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import cn.lingox.android.bin.traveler.info.InfoTraveler;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.view.BaseHolderView;
import cn.lingox.android.test.R;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class TravelerHView extends BaseHolderView{
    @Bind(R.id.traveler_item_card)
    CardView mRoot;
    @Bind(R.id.traveler_item_img_avatar)
    ImageView mImgAvatar;
    @Bind(R.id.traveler_item_txt_name)
    TextView mTxtName;
    @Bind(R.id.traveler_item_txt_time)
    TextView mTxtTime;
    @Bind(R.id.traveler_item_txt_msg)
    TextView mTxtMsg;


    private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd");

    public TravelerHView(Context pContext){
        super(pContext, R.layout.traveler_item);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoTraveler tInfo = (InfoTraveler)pInfo;

//        mTxtName.setText(tInfo.);
        mTxtTime.setText(mSdf.format(tInfo.startTime) + "~" + mSdf.format(tInfo.endTime));
        mTxtMsg.setText(tInfo.text);

//        ImgLoader.getImgLoader(mContext)
//                .displayImage(mImg, tInfo.image21, R.drawable.ic_img_default);

        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.post(new EventLocalClicked(pPosition, mImg));
            }
        });
    }

}
