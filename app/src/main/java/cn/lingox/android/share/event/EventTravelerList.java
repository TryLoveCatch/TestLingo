package cn.lingox.android.share.event;

import java.util.List;

import cn.lingox.android.bin.traveler.info.InfoTraveler;
import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventTravelerList extends EventBase {
    public List<InfoTraveler> data;

    public EventTravelerList(List<InfoTraveler> data){
        super();
        this.data = data;
    }

    public EventTravelerList(boolean pResultSuc, String pResultMsg){
        super(pResultSuc, pResultMsg);
    }
}
