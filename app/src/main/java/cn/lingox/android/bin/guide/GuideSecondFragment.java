package cn.lingox.android.bin.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.lingox.android.test.R;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 7/14/14
 * Time: 11:10 PM
 * Desc: ThirdGuideFragment
 */
public class GuideSecondFragment extends GuideBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guide_second, container, false);
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{
                R.id.guide_item_1,
                R.id.guide_item_2,
                R.id.guide_item_3,
                R.id.guide_item_4
        };
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_second;
    }
}
