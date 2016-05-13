package cn.lingox.android.bin.local.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import cn.lingox.android.bin.local.info.InfoLocal;
import cn.lingox.android.framework.cache.ImgLoader;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.framework.view.BaseHolderView;
import cn.lingox.android.share.event.EventLocalClicked;
import cn.lingox.android.test.R;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class LocalHView extends BaseHolderView{
    @Bind(R.id.local_item_card)
    CardView mRoot;
    @Bind(R.id.local_item_img)
    ImageView mImg;
    @Bind(R.id.local_item_lin_like)
    LinearLayout mLinLike;
    @Bind(R.id.local_item_txt_like)
    TextView mTxtLike;
    @Bind(R.id.local_item_img_like)
    ImageView mImgLike;
    @Bind(R.id.local_item_img_avatar)
    ImageView mImgAvatar;
    @Bind(R.id.local_item_txt_title)
    TextView mTxtTitle;
    @Bind(R.id.local_item_txt_time)
    TextView mTxtTime;


    private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd");

    public LocalHView(Context pContext){
        super(pContext, R.layout.local_item);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoLocal tInfo = (InfoLocal)pInfo;

        mTxtTitle.setText(tInfo.title);
        mTxtTime.setText(mSdf.format(tInfo.createdAt));

//        Picasso.with(mContext)
//                .load(tInfo.image21)
//                .placeholder(R.drawable.ic_img_default)
//                .into(mImg);
        ImgLoader.getImgLoader(mContext)
                .displayImage(mImg, tInfo.image21, R.drawable.ic_img_default);

        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.post(new EventLocalClicked(pPosition, mImg));
            }
        });
    }

}
