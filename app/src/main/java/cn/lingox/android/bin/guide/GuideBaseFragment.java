package cn.lingox.android.bin.guide;

import android.support.v4.app.Fragment;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 7/14/14
 * Time: 10:36 PM
 * Desc: BaseGuideFragment
 */
public abstract class GuideBaseFragment extends Fragment {

    public abstract int[] getChildViewIds() ;

    public abstract int getRootViewId();
}
