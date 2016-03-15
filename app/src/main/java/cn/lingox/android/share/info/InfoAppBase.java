package cn.lingox.android.share.info;

import com.google.gson.annotations.SerializedName;

import cn.lingox.android.framework.data.InfoBase;

/**
 * Created by lipeng21 on 2015/11/10.
 */
public class InfoAppBase<T> extends InfoBase{
    public final static int CODE_SUC = 200;

    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public T results;
    @SerializedName("remark")
    public String remark;//注释
}
