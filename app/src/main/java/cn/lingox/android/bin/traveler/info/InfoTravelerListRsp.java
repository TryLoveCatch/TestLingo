package cn.lingox.android.bin.traveler.info;

import java.util.List;

import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.info.InfoAppBase;

/**
 * Created by lipeng21 on 2016/3/15.
 */
public class InfoTravelerListRsp extends InfoAppBase<InfoTravelerListRsp.Data> {
    public static class Data extends InfoBase{
        public List<InfoTraveler> demands;
    }
}
