package cn.lingox.android.share.event;

import cn.lingox.android.bin.user.info.InfoUser;
import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventUserLogin extends EventBase {
    public InfoUser data;

    public EventUserLogin(InfoUser data){
        super();
        this.data = data;
    }

    public EventUserLogin(boolean pResultSuc, String pResultMsg){
        super(pResultSuc, pResultMsg);
    }
}
