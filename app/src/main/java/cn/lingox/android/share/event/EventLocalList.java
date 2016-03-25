package cn.lingox.android.share.event;

import java.util.List;

import cn.lingox.android.bin.local.info.InfoLocal;
import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventLocalList extends EventBase {
    public List<InfoLocal> data;

    public EventLocalList(List<InfoLocal> data){
        super();
        this.data = data;
    }

    public EventLocalList(boolean pResultSuc, String pResultMsg){
        super(pResultSuc, pResultMsg);
    }
}
