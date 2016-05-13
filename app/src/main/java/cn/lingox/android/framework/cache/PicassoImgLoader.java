package cn.lingox.android.framework.cache;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by lipeng21 on 2016/3/28.
 */
public class PicassoImgLoader implements IImgLoader{

    private Context mContext;

    public PicassoImgLoader(Context pContext){
        mContext = pContext;

        final Picasso tPicasso = new Picasso.Builder(pContext)
//                .downloader(new OkHttpDownloader(okHttpClient))
                .build();

        //客户端应该在任何需要的时候来创建这个实例
        //以防万一，替换掉那个单例对象
        Picasso.setSingletonInstance(tPicasso);
    }

    @Override
    public void displayImage(ImageView pImageView, String pUrl) {
        Picasso.with(mContext)
                .load(pUrl)
                .into(pImageView);
    }

    @Override
    public void displayImage(ImageView pImageView, String pUrl, int pPlaceholderResId){
        Picasso.with(mContext)
                .load(pUrl)
                .placeholder(pPlaceholderResId)
                .into(pImageView);
    }
}
