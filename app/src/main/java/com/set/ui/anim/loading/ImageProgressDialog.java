package com.set.ui.anim.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.set.ui.R;

/**
 * 1:继承AlterDialog，没有使用Builder内部类.
 * 2:构造方法传入theme定义了dialog的样式.
 * 3:使用了onCreate方法.
 *
 * @author chenguax
 */
public class ImageProgressDialog extends AlertDialog {
    private RotateImage mRotateImage;
    private TextView mContentText;

    public ImageProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /*show的时候运行*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.progress_layout);
        mRotateImage = findViewById(R.id.rotate_bar);
        mRotateImage.start();
        mContentText = findViewById(R.id.progress_content);
    }

    public void stopRotate() {
        mRotateImage.stop();
    }

    public void setContentText(int stringId) {
        mContentText.setText(stringId);
    }

    public void setContentText(String msg) {
        mContentText.setText(msg);
    }

}

