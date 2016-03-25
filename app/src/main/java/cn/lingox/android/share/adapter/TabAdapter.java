package cn.lingox.android.share.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentPagerAdapter  {
	private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();

	public TabAdapter(FragmentManager pManager) {
		super(pManager);
	}

	public void setData(ArrayList<Fragment> pFragmentList){
		this.mFragmentList = pFragmentList;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	//自定义Tab的时候
	@Override
	public CharSequence getPageTitle(int position) {
		return null;
	}
}
