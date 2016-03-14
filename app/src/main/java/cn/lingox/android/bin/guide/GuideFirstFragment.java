package cn.lingox.android.bin.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import cn.lingox.android.R;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 7/14/14
 * Time: 11:10 PM
 * Desc: FirstGuideFragment
 */
public class GuideFirstFragment extends GuideBaseFragment {

    final long ANIMATION_DURATION = 500;
    final long ANIMATION_OFFSET = 200;

    private int[] mAnimationViewIds = {
            R.id.guide_item_2, R.id.guide_item_3, R.id.guide_item_4,
            R.id.guide_item_5, R.id.guide_item_6, R.id.guide_item_7,
            R.id.guide_item_8, R.id.guide_item_9, R.id.guide_item_10,
            R.id.guide_item_11, R.id.guide_item_12, R.id.guide_item_13
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guide_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < mAnimationViewIds.length; i++) {
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.guide_items);
            animation.setDuration(ANIMATION_DURATION);
            animation.setStartOffset(ANIMATION_OFFSET * i);
            view.findViewById(mAnimationViewIds[i]).startAnimation(animation);
        }
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{
                R.id.guide_item_1
        };
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_first;
    }
}
