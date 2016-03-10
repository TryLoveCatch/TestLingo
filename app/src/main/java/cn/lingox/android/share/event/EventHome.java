package cn.lingox.android.share.event;

import java.util.List;

import cn.lingox.android.dao.InfoDaoHome;
import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventHome extends EventBase {
    public List<InfoDaoHome> data;

    public EventHome(List<InfoDaoHome> data){
        super();
        this.data = data;
    }

    public EventHome(boolean pResultSuc, String pResultMsg){
        super(pResultSuc, pResultMsg);
    }
}
