package cn.lingox.android.bin.guide;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.lingox.android.test.R;

/**
 * Created by lipeng21 on 2016/3/11.
 */
public class IndicatorView {
        public final static int DEFAULT_COLOR = 1;

        private Context mContext;
        private LinearLayout mDotLayout;
        private List<ImageView> mDots;
        private int mSlideCount;
        int selectedDotColor = DEFAULT_COLOR;
        int unselectedDotColor = DEFAULT_COLOR;
        int mCurrentposition;

        private static final int FIRST_PAGE_NUM = 0;

        public View newInstance(@NonNull Context context) {
            mContext = context;
            mDotLayout = (LinearLayout) View.inflate(context, R.layout.guide_indicator_view, null);
            return mDotLayout;
        }

        public void initialize(int slideCount) {
            mDots = new ArrayList<>();
            mSlideCount = slideCount;
            selectedDotColor = -1;
            unselectedDotColor = -1;

            for (int i = 0; i < slideCount; i++) {
                ImageView dot = new ImageView(mContext);
                dot.setImageDrawable(mContext.getDrawable(R.drawable.guide_indicator_dot_grey));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                mDotLayout.addView(dot, params);
                mDots.add(dot);
            }

            selectPosition(FIRST_PAGE_NUM);
        }

        public void selectPosition(int index) {
            mCurrentposition = index;
            for (int i = 0; i < mSlideCount; i++) {
                int drawableId = (i == index) ? (R.drawable.guide_indicator_dot_white) : (R.drawable.guide_indicator_dot_grey);
                Drawable drawable = mContext.getDrawable(drawableId);
                if (selectedDotColor != DEFAULT_COLOR && i == index)
                    drawable.mutate().setColorFilter(selectedDotColor, PorterDuff.Mode.SRC_IN);
                if (unselectedDotColor != DEFAULT_COLOR && i != index)
                    drawable.mutate().setColorFilter(unselectedDotColor, PorterDuff.Mode.SRC_IN);
                mDots.get(i).setImageDrawable(drawable);
            }
        }

        public void setSelectedIndicatorColor(int color) {
            selectedDotColor = color;
            selectPosition(mCurrentposition);
        }

        public void setUnselectedIndicatorColor(int color) {
            unselectedDotColor = color;
            selectPosition(mCurrentposition);
        }
}
