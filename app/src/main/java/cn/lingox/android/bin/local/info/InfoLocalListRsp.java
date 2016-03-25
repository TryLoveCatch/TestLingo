package cn.lingox.android.bin.local.info;

import java.util.List;

import cn.lingox.android.framework.data.InfoBase;
import cn.lingox.android.share.info.InfoAppBase;

/**
 * Created by lipeng21 on 2016/3/15.
 */
public class InfoLocalListRsp extends InfoAppBase<InfoLocalListRsp.Data> {
    public static class Data extends InfoBase{
        public List<InfoLocal> paths;
    }
}
