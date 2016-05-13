package cn.lingox.android.framework.cache;

import android.content.Context;

/**
 * 全局ImageLoader管理
 * @Package com.jiaoyang.video.framework.cache
 * @ClassName ImgLoader
 * @author TryLoveCatch
 * @date 2014年5月21日 下午10:39:05
 */
public class ImgLoader {

    private static IImgLoader mImgLoader;

    public static IImgLoader getImgLoader(Context pContext){
        if(mImgLoader==null) {
            synchronized (ImgLoader.class) {
                if(mImgLoader==null) {
                    mImgLoader = new PicassoImgLoader(pContext);
                }
            }
        }
        return mImgLoader;
    }


}
