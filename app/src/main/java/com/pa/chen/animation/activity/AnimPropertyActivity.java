package com.pa.chen.animation.activity;


import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.pa.chen.animation.R;

/*
 * ObjectAnimator 动画的执行类 ValueAnimator 动画的执行类 AnimatorSet
 * 用于控制一组动画的执行：线性，一起，每个动画的先后执行等。 AnimatorInflater 用户加载属性动画的xml文件 TypeEvaluator
 * 类型估值，主要用于设置动画操作属性的值。 TimeInterpolator 时间插值
 */
public class AnimPropertyActivity extends Activity implements View.OnClickListener {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_property);
        initView();
    }

    protected void initView() {
        imageView = (ImageView) findViewById(R.id.id_ball);
        Button mBtnRotate = (Button) findViewById(R.id.btn_rotate);
        mBtnRotate.setOnClickListener(this);
        Button mBtnValuesHolder = (Button) findViewById(R.id.btn_property_values_holder);
        mBtnValuesHolder.setOnClickListener(this);
        Button mBtnPWX = (Button) findViewById(R.id.btn_paowuxian);
        mBtnPWX.setOnClickListener(this);
        Button mBtnViewAnim = (Button) findViewById(R.id.btn_view_anim);
        mBtnViewAnim.setOnClickListener(this);
    }

    //旋转
    public void rotateXAnimRun(final View view) {
        /*
         * 动画更新的时候会不断调用setPropName来更新属性
		 * 所以需要有setter和getter,在setRotationX的时候如果没有重绘,
		 * 在AnimatorUpdateListener里还需增加view.invalidate();
		 */
        /* rotationX属性就是通过setRotationX在一定duration时间内不断给属性赋值 从0到180 */
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationX", 0.0F,
                180.0F).setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = animation.getAnimatedFraction();
                Log.d(TAG.TAG_2, cVal + "");
            }
        });
        anim.start();
    }

    //实现一个动画更改多个效果：使用propertyValuesHolder 注意 是一个动画 并不是多个动画的叠加
    //一个PropertyValuesHolder代表一个个属性 在一定时间的值的范围
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.5f, 0.0f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.5f, 0.0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ)
                .setDuration(2000).start();
    }

    //抛物线ofInt,ofFloat由PointF代替
    public void paowuxian(final View target) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PaoWuLineEvaluator(), new PointF(0, 0));
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new PaoWuLineUpdateLister(target));
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parent = (ViewGroup) target.getParent();
                if (parent != null) {
                    //parent.removeView(view);
                }
            }
        });
        valueAnimator.start();
    }

    private class PaoWuLineEvaluator implements TypeEvaluator<PointF> {
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            Log.d(TAG.TAG_2, fraction * 3 + "");
            // x方向100px/s ，则y方向0.5 * 10 * t
            PointF resultPointF = new PointF();
            resultPointF.x = 100 * fraction * 3;
            resultPointF.y = 0.5f * 100 * (fraction * 3) * (fraction * 3);
            return resultPointF;
        }
    }

    private class PaoWuLineUpdateLister implements AnimatorUpdateListener {
        private View target;

        public PaoWuLineUpdateLister(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            ViewCompat.setX(target, pointF.x);
            ViewCompat.setY(target, pointF.y);
           // ViewCompat.setAlpha(target, 1 - animation.getAnimatedFraction());
        }
    }

    public void viewAnim(final View view) {
        view.animate()
                .alpha(0)//3秒内变透明 ，并且往y方向增加1200像素
                .y(1200).setDuration(3000)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {/*view动画结束后 执行*/
                        view.setY(0); //最后在父View坐标系内Y值为0处。
                        view.setAlpha(1.0f);
                    }
                }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rotate:
                rotateXAnimRun(imageView);
                break;
            case R.id.btn_property_values_holder:
                propertyValuesHolder(imageView);
                break;
            case R.id.btn_paowuxian:
                paowuxian(imageView);
                break;
            case R.id.btn_view_anim:
                viewAnim(imageView);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
