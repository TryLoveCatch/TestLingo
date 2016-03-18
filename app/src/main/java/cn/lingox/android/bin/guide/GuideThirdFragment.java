package cn.lingox.android.bin.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import cn.lingox.android.test.R;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 7/14/14
 * Time: 11:10 PM
 * Desc: ThirdGuideFragment
 */
public class GuideThirdFragment extends GuideBaseFragment {

    View mLayoutLogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guide_third, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayoutLogo = view.findViewById(R.id.guide_item_1);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.guide_logo);
        mLayoutLogo.startAnimation(anim);
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{};
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_third;
    }
}
