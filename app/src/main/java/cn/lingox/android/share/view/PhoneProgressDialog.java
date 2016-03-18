package cn.lingox.android.share.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lingox.android.test.R;
import cn.lingox.android.util.UtilString;

/**
 * Created by lipeng21 on 2016/3/14.
 */
public class PhoneProgressDialog extends Dialog{

    public PhoneProgressDialog(Context context) {
        super(context);
    }
    public PhoneProgressDialog(Context context, int style) {
        super(context, style);
    }

    @Override
    public void setTitle(CharSequence message) {
        if(message!=null) {
            TextView tTxt = (TextView) findViewById(R.id.progress_dialog_txt_msg);
            if (tTxt != null) {
                tTxt.setText(message);
            }
        }
    }

    public static Dialog createProgressDialog(Context context, boolean cancelable) {
        return createProgressDialog(context, context.getString(R.string.progress_msg_default), null, cancelable);
    }

    public static Dialog createProgressDialog(Context context, String msg, boolean cancelable) {
        return createProgressDialog(context, msg, null, cancelable);
    }

    public static Dialog createProgressDialog(Context context, int msgResId,
                                              DialogInterface.OnCancelListener cancelListener, boolean cancelable) {
        return createProgressDialog(context, context.getString(msgResId), cancelListener, cancelable);
    }

    public static Dialog createProgressDialog(Context context, String msg,
                                              DialogInterface.OnCancelListener cancelListener, boolean cancelable) {
        Dialog mProgressDialog = new PhoneProgressDialog(context, R.style.Progress_Dialog);
        View tRoot = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        mProgressDialog.setContentView(tRoot, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

        mProgressDialog.setCancelable(true);
        mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if(UtilString.isNotBlank(msg)){
            TextView tTxt = (TextView)mProgressDialog.findViewById(R.id.progress_dialog_txt_msg);
            tTxt.setText(msg);
            tTxt.setVisibility(View.VISIBLE);
        }
        mProgressDialog.findViewById(R.id.progress_dialog_loading_pb).setVisibility(View.VISIBLE);
        if (null != cancelListener)
            mProgressDialog.setOnCancelListener(cancelListener);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(cancelable);
        return mProgressDialog;
    }
}
