package cn.lingox.android.framework.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import cn.lingox.android.framework.data.InfoBase;

/**
 * Created by lipeng21 on 2015/11/9.
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseHolderView>{

    private final static int TYPE_FOOTER = 100;

    private Context mContext;
    private Class<? extends BaseHolderView>[] mHolderViews;
    private List<? extends InfoBase> mArrData;

    private boolean mHasFooter;
    private boolean mIsHasMore;

    public BaseAdapter(Context pContext){
        this.mContext = pContext;
        mHasFooter = true;
        mIsHasMore = true;
    }

    public BaseAdapter setHolderViews(Class<? extends BaseHolderView>... pHolderViews){
        this.mHolderViews = pHolderViews;
        return this;
    }

    public BaseAdapter setData(List<? extends InfoBase> pArrDats){
        this.mArrData = pArrDats;
        return this;
    }

    public void setIsHasMore(boolean pIsHasMore){
        this.mIsHasMore = pIsHasMore;
    }

    public void setIsHasFooter(boolean pHasFooter){
        this.mHasFooter = pHasFooter;
    }

    @Override
    public BaseHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            if (viewType == TYPE_FOOTER) {
                return new FooterViewHolder(mContext);
            }
            return (BaseHolderView) this.mHolderViews[viewType].getConstructor(Context.class)
                        .newInstance(this.mContext);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolderView holder, int position) {
        if(mHasFooter && position!=0 && position == mArrData.size()){
            ((FooterViewHolder)holder).bindData(mIsHasMore);
        }else {
            holder.bindData(getItem(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if(mArrData!=null){
            if(mHasFooter && mArrData.size()!=0){
                return mArrData.size() + 1;
            }
            return mArrData.size();
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mHasFooter && position!=0 && position == mArrData.size()){
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }


    public InfoBase getItem(int pPosition){
        return mArrData!=null && mArrData.size() > 0 ? mArrData.get(pPosition) : null;
    }
}
