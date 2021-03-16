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
import com.set.view.widget.ImageProgressDialog;
import com.set.view.anim.dialog3d.Dialog3dAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
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

        String[] mainItem = {
                "补间动画",
                "属性动画",
                "帧动画",
                "插值器",
                "Scroller弹性动画",
                "图片快速闪动替换",
                "开门动画",
                "爆炸效果",
                "加载效果",
                "3d对话框动画",
                "View属性"
        };

        for (int m = 0; m < 11; m++) {
            ItemEntry<Type3Entity> itemsEntry0 = new ItemEntry();
            itemsEntry0.data = new Type3Entity(mainItem[m]);
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
                startActivity(new Intent(MainViewActivity.this, TweenAnimActivity.class));
                break;
            case 1://属性
                startActivity(new Intent(MainViewActivity.this, PropertyAnimActivity.class));
                break;
            case 2://帧动画
                startActivity(new Intent(MainViewActivity.this, FrameAnimActivity.class));
                break;
            case 3://插值器
                startActivity(new Intent(MainViewActivity.this, TweenInterpolatorActivity.class));
                break;
            case 4://scroller
                startActivity(new Intent(MainViewActivity.this, ScrollerActivity.class));
                break;
            case 5: //ImageView快速闪动
                startActivity(new Intent(MainViewActivity.this, FasterImageAnimActivity.class));
                break;
            case 6://opendoor
                startActivity(new Intent(MainViewActivity.this, OpenDoorActivity.class));
                break;
            case 7://爆炸
                startActivity(new Intent(MainViewActivity.this, ExplosionActivity.class));
                break;
            case 8://loading
                startActivity(new Intent(MainViewActivity.this, LoadingActivity.class));
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
                startActivity(new Intent(MainViewActivity.this, ViewTranslationActivity.class));
                break;
            default:
                break;


        }
    }


}
