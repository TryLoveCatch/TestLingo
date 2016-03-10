package cn.lingox.android.share.event;

import cn.lingox.android.bin.detail.InfoDetail;
import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventDetail extends EventBase {
    public InfoDetail data;

    public EventDetail(InfoDetail data){
        super();
        this.data = data;
    }

    public EventDetail(boolean pResultSuc, String pResultMsg){
        super(pResultSuc, pResultMsg);
    }
}
