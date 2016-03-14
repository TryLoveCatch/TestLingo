package cn.lingox.android.bin.meizi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import cn.lingox.android.R;
import cn.lingox.android.dao.InfoDaoMeizi;
import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.framework.event.EventBus;
import cn.lingox.android.framework.view.BaseHolderView;
import cn.lingox.android.share.event.EventMeiziClicked;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class MeiziHView extends BaseHolderView{
    @Bind(R.id.meizi_item_rlt)
    RelativeLayout mRlt;
    @Bind(R.id.meizi_item_img)
    ImageView mImg;

    public MeiziHView(Context pContext){
        super(pContext, R.layout.meizi_item);
//        mImg.setOriginalSize(50, 50);
    }

    @Override
    protected void bindData(InfoBase pInfo, final int pPosition) {
        final InfoDaoMeizi tInfo = (InfoDaoMeizi)pInfo;

//        Glide.with(mContext)
//                .load(tInfo.url)
//                .centerCrop()
//                .crossFade()
//                .into(mImg)
//                .getSize(new SizeReadyCallback() {
//                    @Override
//                    public void onSizeReady(int width, int height) {
//                        if(!mRlt.isShown()){
//                            mRlt.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });

        Picasso.with(mContext)
                .load(tInfo.url)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        ViewGroup.LayoutParams tParams = mImg.getLayoutParams();
                        tParams.height = Math.round(tParams.width * 1.0f * bitmap.getHeight() / bitmap.getWidth());
                        mImg.setImageBitmap(bitmap);
                        if (!mRlt.isShown()) {
                            mRlt.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.post(new EventMeiziClicked(pPosition, mImg));
            }
        });
    }

}
