package com.set.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.set.view.anim.dialog3d.Anim3dActivity;
import com.set.view.anim.fastflash.FasterImageAnimActivity;
import com.set.view.anim.sample.FrameAnimActivity;
import com.set.view.anim.opendoor.OpenDoorActivity;
import com.set.view.anim.scroller.ScrollerActivity;
import com.set.view.anim.sample.PropertyAnimActivity;
import com.set.view.anim.sample.TweenAnimActivity;
import com.set.view.anim.sample.AnimInterpolatorActivity;
import com.set.view.toolview.ViewTranslationActivity;
import com.set.view.anim.explosion.ExplosionActivity;
import com.set.view.anim.loading.LoadingActivity;
import com.set.view.scroll.FloorType;
import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.adapter.BuAdapter;
import com.set.view.scroll.entity.ItemEntry;
import com.set.view.scroll.entity.Type3Entity;
import com.set.view.surface.SurfaceMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public class MainViewActivity extends AppCompatActivity {
    private List<ItemEntry> mDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BuAdapter mainAdapter;
    List<EntryItem> mainItemMap = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mRecyclerView = findViewById(R.id.main_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);


        mainItemMap.add(new EntryItem(0, "补间动画", TweenAnimActivity.class));
        mainItemMap.add(new EntryItem(1, "属性动画", PropertyAnimActivity.class));
        mainItemMap.add(new EntryItem(2, "帧动画", FrameAnimActivity.class));
        mainItemMap.add(new EntryItem(3, "插值器", AnimInterpolatorActivity.class));
        mainItemMap.add(new EntryItem(4, "Scroller弹性", ScrollerActivity.class));
        mainItemMap.add(new EntryItem(5, "图片快速Flash", FasterImageAnimActivity.class));
        mainItemMap.add(new EntryItem(6, "开门动画", OpenDoorActivity.class));
        mainItemMap.add(new EntryItem(7, "爆炸效果", ExplosionActivity.class));
        mainItemMap.add(new EntryItem(8, "Loading", LoadingActivity.class));
        mainItemMap.add(new EntryItem(9, "3d对话框动画", Anim3dActivity.class));
        mainItemMap.add(new EntryItem(10, "View属性", ViewTranslationActivity.class));
        mainItemMap.add(new EntryItem(11, "Surface", SurfaceMainActivity.class));

        for (int m = 0; m < mainItemMap.size(); m++) {
            ItemEntry<Type3Entity> itemsEntry0 = new ItemEntry();
            itemsEntry0.data = new Type3Entity(mainItemMap.get(m).mDesc);
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
        EntryItem item = mainItemMap.get(position);

        Class toClass = item.mClass;
        toActivity(toClass);
    }

    void toActivity(Class<?> cls) {
        Intent intent = new Intent(MainViewActivity.this, cls);
        startActivity(intent);
    }

    class EntryItem {
        int position;
        String mDesc;
        Class mClass;

        public EntryItem(int position, String mDesc, Class mClass) {
            this.position = position;
            this.mDesc = mDesc;
            this.mClass = mClass;
        }
    }
}
