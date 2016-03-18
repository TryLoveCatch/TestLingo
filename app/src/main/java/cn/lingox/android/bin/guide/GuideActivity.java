package cn.lingox.android.bin.guide;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.Bind;
import cn.lingox.android.test.R;
import cn.lingox.android.bin.user.UserLoginFragment;
import cn.lingox.android.framework.BaseActivity;
import cn.lingox.android.share.fragment.SharedFragmentActivity;

public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private final float PARALLAX_COEFFICIENT = 1.2f;
    private final float DISTANCE_COEFFICIENT = 0.5f;

    //===============界面变量==============
    @Bind(R.id.guide_pager)
    ViewPager mPager;
    GuidePagerAdapter mAdapter;
    IndicatorView mIndicatorView;

    @Bind(R.id.guide_login)
    Button mBtnLogin;
    @Bind(R.id.guide_regist)
    Button mBtnRegist;
    //===============逻辑变量==============
    SparseArray<int[]> mLayoutViewIdsMap = new SparseArray<int[]>();
    //===============生命周期==============

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setForbidStartActivityAnimation(true);
        setForbidFinishActivityGesture(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState, R.layout.guide);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //===============事件接口==============
    @Override
    public void initViewProperty() {
        mAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        addGuide(new GuideFirstFragment());
        addGuide(new GuideSecondFragment());
        addGuide(new GuideThirdFragment());

        mPager.setAdapter(mAdapter);
        mPager.setPageTransformer(true, new ParallaxTransformer(PARALLAX_COEFFICIENT, DISTANCE_COEFFICIENT));
        mPager.setOnPageChangeListener(new GuidePageChangeListener());

        mIndicatorView = new IndicatorView();
        ((FrameLayout)findViewById(R.id.guide_indicator_lin)).addView(mIndicatorView.newInstance(this));
        mIndicatorView.initialize(mAdapter.getCount());

        mBtnLogin.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_login:
                SharedFragmentActivity.startFragmentActivity(this, UserLoginFragment.class, true);
                break;
            case R.id.guide_regist:
                break;
        }
    }

    //===============对外方法==============
    //===============私有方法==============
    private void addGuide(GuideBaseFragment fragment) {
        mAdapter.addItem(fragment);
        mLayoutViewIdsMap.put(fragment.getRootViewId(), fragment.getChildViewIds());
    }

    class ParallaxTransformer implements ViewPager.PageTransformer {

        float parallaxCoefficient;
        float distanceCoefficient;

        public ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient) {
            this.parallaxCoefficient = parallaxCoefficient;
            this.distanceCoefficient = distanceCoefficient;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void transformPage(View page, float position) {
            float scrollXOffset = page.getWidth() * parallaxCoefficient;

//            ViewGroup pageViewWrapper = (ViewGroup) page;
            @SuppressWarnings("SuspiciousMethodCalls")
            int[] layer = mLayoutViewIdsMap.get(page.getId());
            for (int id : layer) {
                View view = page.findViewById(id);
                if (view != null) {
                    view.setTranslationX(scrollXOffset * position);
                }
                scrollXOffset *= distanceCoefficient;
            }
        }
    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        ArgbEvaluator mColorEvaluator;

        int mPageWidth, mTotalScrollWidth;

        int mGuideStartBackgroundColor, mGuideEndBackgroundColor;

        public GuidePageChangeListener() {
            mColorEvaluator = new ArgbEvaluator();

            mPageWidth = getWindowManager().getDefaultDisplay().getWidth();
            mTotalScrollWidth = mPageWidth * mAdapter.getCount();

            mGuideStartBackgroundColor = getResources().getColor(R.color.colorPrimaryDark);
            mGuideEndBackgroundColor = getResources().getColor(R.color.colorAccent);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float ratio = (mPageWidth * position + positionOffsetPixels) / (float) mTotalScrollWidth;
            Integer color = (Integer) mColorEvaluator.evaluate(ratio, mGuideStartBackgroundColor, mGuideEndBackgroundColor);
            mPager.setBackgroundColor(color);
        }

        @Override
        public void onPageSelected(int position) {
            mIndicatorView.selectPosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
