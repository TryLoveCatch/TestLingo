package cn.lingox.android.share.event;

import android.view.View;

import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventMeiziClicked extends EventBase{
    public int position;
    public View view;
    public EventMeiziClicked(int pPosition, View pView){
        super();
        this.position = pPosition;
        this.view = pView;
    }
}
