package cn.lingox.android.share.event;

import cn.lingox.android.framework.event.EventBase;

/**
 * Created by lipeng21 on 2015/11/26.
 */
public class EventDetailClicked extends EventBase{
    public String url;
    public boolean isVideo;
    public EventDetailClicked(String url, boolean isVideo){
        super();
        this.url = url;
        this.isVideo = isVideo;
    }
}
