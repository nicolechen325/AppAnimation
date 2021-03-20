package com.set.view.anim;

import com.set.view.BaseGridViewActivity;
import com.set.view.RouteItemEntry;
import com.set.view.anim.dialog3d.Anim3dActivity;
import com.set.view.anim.explosion.ExplosionActivity;
import com.set.view.anim.fastflash.FasterImageAnimActivity;
import com.set.view.anim.loading.LoadingActivity;
import com.set.view.anim.opendoor.OpenDoorActivity;
import com.set.view.anim.sample.AnimInterpolatorActivity;
import com.set.view.anim.sample.FrameAnimActivity;
import com.set.view.anim.sample.PropertyAnimActivity;
import com.set.view.anim.sample.TweenAnimActivity;
import com.set.view.anim.scroller.ScrollerActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * 动画入口
 */
public class AnimRouteActivity extends BaseGridViewActivity {

    @Override
    public List<RouteItemEntry> initItemData() {

        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, "补间动画", TweenAnimActivity.class));
        list.add(new RouteItemEntry(1, "属性动画", PropertyAnimActivity.class));
        list.add(new RouteItemEntry(2, "帧动画", FrameAnimActivity.class));
        list.add(new RouteItemEntry(3, "插值器", AnimInterpolatorActivity.class));
        list.add(new RouteItemEntry(4, "Scroller弹性", ScrollerActivity.class));
        list.add(new RouteItemEntry(5, "图片快速Flash", FasterImageAnimActivity.class));
        list.add(new RouteItemEntry(6, "开门动画", OpenDoorActivity.class));
        list.add(new RouteItemEntry(7, "爆炸效果", ExplosionActivity.class));
        list.add(new RouteItemEntry(8, "Loading", LoadingActivity.class));
        list.add(new RouteItemEntry(9, "3d对话框动画", Anim3dActivity.class));
        return list;
    }
}
