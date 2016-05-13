package cn.lingox.android.framework.cache;

import android.widget.ImageView;

/**
 * Created by lipeng21 on 2016/3/28.
 */
public interface IImgLoader {

    void displayImage(ImageView pImageView, String pUrl);

    void displayImage(ImageView pImageView, String pUrl, int pPlaceholderResId);

}
