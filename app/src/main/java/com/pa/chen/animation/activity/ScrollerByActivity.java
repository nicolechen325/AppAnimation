package com.pa.chen.animation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pa.chen.animation.R;

//执行scrollTo的View视图内容就会滚动，而不是他本很滚动。
public class ScrollerByActivity extends Activity implements View.OnClickListener{
    //TextView内容滚动
    TextView tvScrollInside;
    //LinearLayout内部存在一个TextView
    LinearLayout mLlInsideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_by);
        initView();
    }

    protected void initView() {
        tvScrollInside=(TextView) findViewById(R.id.tv_scroll_inside);
        mLlInsideView=(LinearLayout) findViewById(R.id.ll_scroll_inside_view);
        Button mBtnScrollBy=(Button)findViewById(R.id.btn_scrollby);
        Button mBtnScrollTo=(Button)findViewById(R.id.btn_scrollto);
        Button mBtnScrollReset=(Button)findViewById(R.id.btn_reset);
        mBtnScrollBy.setOnClickListener(this);
        mBtnScrollTo.setOnClickListener(this);
        mBtnScrollReset.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scrollby:
                 //滚动条x轴左移(负值)50,控件的内容右移50
                mLlInsideView.scrollBy(-50, 0);
                tvScrollInside.scrollBy(-50, 0);
                break;
            case R.id.btn_scrollto:
                 //滚动条x轴左移到(负值)200位置,控件的内容右移到50位置
                mLlInsideView.scrollTo(-200, 0);
                tvScrollInside.scrollTo(-200, 0);
                break;
            case R.id.btn_reset:
                //复位
                mLlInsideView.scrollTo(0, 0);
                tvScrollInside.scrollTo(0, 0);
                break;
        }
    }

}
