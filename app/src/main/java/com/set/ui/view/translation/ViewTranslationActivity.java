package com.set.ui.view.translation;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.set.ui.R;

/**
 * View 位置属性
 */
public class ViewTranslationActivity extends AppCompatActivity {
    public static final String TAG_3 = "ViewTranslationActivity";
    private TextView mTextLog;
    private TranslationView mTranslationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_translation);
        initView();
    }

    protected void initView() {
        mTranslationView = findViewById(R.id.translation_view);
        mTextLog = findViewById(R.id.tv_view_log);
        mTranslationView.setOnLogPrint(v -> {
            mTextLog.setText(v);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_transiation, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_select0:
                mTranslationView.setMoveView(0);
                break;
            case R.id.action_select1:
                mTranslationView.setMoveView(1);
                break;
            case R.id.action_select2:
                mTranslationView.setMoveView(2);
                break;
            case R.id.action_select3:
                mTranslationView.setMoveView(3);
                break;
            case R.id.action_select4:
                mTranslationView.setMoveView(4);
                break;
        }

        return true;
    }
}
