package cn.lingox.android.bin.detail;

import android.content.Context;

import cn.lingox.android.framework.view.BaseAdapter;

/**
 * Created by lipeng21 on 2015/12/29.
 */
public class DetailAdapter extends BaseAdapter{

    public DetailAdapter(Context pContext) {
        super(pContext);
    }

    @Override
    public int getItemViewType(int position) {
        int tType = super.getItemViewType(position);
        if(tType > 0) return tType;
        if(getItem(position) instanceof InfoDetailTitle){
            return 0;
        }else{
            return 1;
        }
    }

}
