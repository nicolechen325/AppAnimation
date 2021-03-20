Android View 工程
动画，图片，视图，滚动，触屏，视频，原理

#### 动画
ObjectAnimator 动画的执行类 ValueAnimator 动画的执行类 AnimatorSet
用于控制一组动画的执行：线性，一起，每个动画的先后执行等。 AnimatorInflater 用户加载属性动画的xml文件 TypeEvaluator
类型估值，主要用于设置动画操作属性的值。 TimeInterpolator 时间插值

补间动画：只需要指定动画开始 动画结束关键帧 以及持续时间、动画变化的中间帧 由系统计算
Interpolator接口控制动画变化的速度 包括匀速 加速减速 抛物线等

[补间动画原理](https://www.jianshu.com/p/62aab211a606)

#### ImageView
ImageView的ScaleType
https://www.jianshu.com/p/64790fce98e2

1，上和左黑线代表拉伸区域，只拉伸这部分区域
2，右和下黑线代表显示内容区域，只在着部分区域显示内容，
如TextView背景设置drawbale是.9图，设置了右边和下边的显示区域，则text会在显示区域中开始显示。
3，error: too many padding sections on right border
原因.9图片的右边与底部出现多条“黑色线”