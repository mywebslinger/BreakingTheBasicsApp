package com.breakingthebasics.common;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import com.breakingthebasics.R;

public class MyProgress {

    Context context;
    private Dialog dialog;

    public MyProgress(Context context){
        this.context=context;

    }
    public void showLoading(@NonNull String message) {
        dialog = new Dialog(context, R.style.TransparentBackground);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_loading);
        dialog.setCancelable(false);
        dialog.show();
       /* mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();*/
    }

    public void hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
    }
}
