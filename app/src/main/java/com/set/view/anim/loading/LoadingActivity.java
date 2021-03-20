package com.set.view.anim.loading;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.set.view.R;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_loading_view);

        TextView loading2 = findViewById(R.id.tv_loading2);
        loading2.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_loading2:
                final ImageProgressDialog prDialog = new ImageProgressDialog(LoadingActivity.this,
                        R.style.dialog);
                prDialog.setCancelable(false);
                prDialog.show();
                prDialog.setContentText("正在加载,请稍后..");//在show之后，否则会空指针.
                prDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        prDialog.stopRotate();
                    }
                });
                break;
            default:
                break;
        }
    }
}
