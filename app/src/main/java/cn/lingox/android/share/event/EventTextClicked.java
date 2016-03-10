package cn.lingox.android.share.event;

import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventTextClicked extends EventBase{
    public String url;
    public EventTextClicked(String url){
        super();
        this.url = url;
    }
}
