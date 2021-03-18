package com.set.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.set.view.anim.sample.FasterImageAnimActivity;
import com.set.view.anim.sample.FrameAnimActivity;
import com.set.view.anim.sample.OpenDoorActivity;
import com.set.view.anim.sample.ScrollerActivity;
import com.set.view.anim.sample.PropertyAnimActivity;
import com.set.view.anim.sample.TweenAnimActivity;
import com.set.view.anim.sample.TweenInterpolatorActivity;
import com.set.view.anim.sample.ViewTranslationActivity;
import com.set.view.anim.explosion.ExplosionActivity;
import com.set.view.anim.loading.LoadingActivity;
import com.set.view.scroll.FloorType;
import com.set.view.scroll.RecyclerScrollActivity;
import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.adapter.BuAdapter;
import com.set.view.scroll.entity.Bean;
import com.set.view.scroll.entity.ItemEntry;
import com.set.view.scroll.entity.MoreViewEntry;
import com.set.view.scroll.entity.Type3Entity;
import com.set.view.surface.SurfaceMainActivity;
import com.set.view.widget.ImageProgressDialog;
import com.set.view.anim.dialog3d.Dialog3dAnim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主页
 */
public class MainViewActivity extends AppCompatActivity {
    private List<ItemEntry> mDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BuAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mRecyclerView = findViewById(R.id.main_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        HashMap<Integer, String> mainItemMap = new HashMap();
        mainItemMap.put(0, "补间动画");
        mainItemMap.put(1, "属性动画");
        mainItemMap.put(2, "帧动画");
        mainItemMap.put(3, "插值器");
        mainItemMap.put(4, "Scroller弹性动画");
        mainItemMap.put(5, "图片快速闪动替换");
        mainItemMap.put(6, "开门动画");
        mainItemMap.put(7, "爆炸效果");
        mainItemMap.put(8, "加载效果");
        mainItemMap.put(9, "3d对话框动画");
        mainItemMap.put(10, "View属性");
        mainItemMap.put(11, "Surface");

        for (int m = 0; m < mainItemMap.size(); m++) {
            ItemEntry<Type3Entity> itemsEntry0 = new ItemEntry();
            itemsEntry0.data = new Type3Entity(mainItemMap.get(m));
            itemsEntry0.itemClickListener = new BaseAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    onItemClick(position);
                }

            };
            itemsEntry0.floorType = FloorType.ITEM_TYPE_3;
            mDataList.add(itemsEntry0);
        }

        mainAdapter = new BuAdapter(this, mDataList);
        mRecyclerView.setAdapter(mainAdapter);

    }

    void onItemClick(int position) {

        switch (position) {
            case 0://补间动画
                toActivity(TweenAnimActivity.class);
                break;
            case 1://属性
                toActivity(PropertyAnimActivity.class);
                break;
            case 2://帧动画
                toActivity(FrameAnimActivity.class);
                break;
            case 3://插值器
                toActivity(TweenInterpolatorActivity.class);
                break;
            case 4://scroller
                toActivity(ScrollerActivity.class);
                break;
            case 5: //ImageView快速闪动
                toActivity(FasterImageAnimActivity.class);
                break;
            case 6://opendoor
                toActivity(OpenDoorActivity.class);
                break;
            case 7://爆炸
                toActivity(ExplosionActivity.class);
                break;
            case 8://loading
                toActivity(LoadingActivity.class);
                break;
            case 9://3ddialog
                final Dialog3dAnim dialog = new Dialog3dAnim(this);
                dialog.show();

                final ImageProgressDialog prDialog = new ImageProgressDialog(MainViewActivity.this,
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
            case 10:
                toActivity(ViewTranslationActivity.class);
                break;
            case 11:
                toActivity(SurfaceMainActivity.class);
                break;
            default:
                break;
        }
    }


    void toActivity(Class<?> cls) {
        Intent intent = new Intent(MainViewActivity.this, cls);
        startActivity(intent);
    }

}
