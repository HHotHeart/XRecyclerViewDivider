package com.littlejerk.xrecyclerviewdivider;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.littlejerk.rvdivider.builder.XGridBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridAdapter mAdapter;
    private GridLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        findViewById(R.id.iv_back).setOnClickListener(mView -> onBackPressed());
        RadioButton rb_v = findViewById(R.id.rb_v);
        RadioButton rb_h = findViewById(R.id.rb_h);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        rb_v.setOnCheckedChangeListener((mCompoundButton, mB) -> {
            if (mB) {
                deleteItemDivider(recyclerView);
                mManager = new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(mManager);
                recyclerView.addItemDecoration(bindXGrid().build());
                mAdapter = new GridAdapter(R.layout.item_grid_vertical_list, getData());
                recyclerView.setAdapter(mAdapter);
            }
        });
        rb_h.setOnCheckedChangeListener((mCompoundButton, mB) -> {
            if (mB) {
                deleteItemDivider(recyclerView);
                mManager = new GridLayoutManager(this, 4, RecyclerView.HORIZONTAL, false);
                recyclerView.setLayoutManager(mManager);
                recyclerView.addItemDecoration(bindXGrid().build());
                mAdapter = new GridAdapter(R.layout.item_gird_horizontal_list, getData());
                recyclerView.setAdapter(mAdapter);
            }
        });

        rb_v.setChecked(true);

    }

    /**
     * ???????????????
     *
     * @return
     */
    public XGridBuilder bindXGrid() {
        return new XGridBuilder(this)
                //????????????????????????float dp ???DimenRes dp
                .setSpacing(2f)//?????????Spacing??????????????????XGridBuilder??????
                .setVLineSpacing(4f)
                .setHLineSpacing(8f)
                //?????????color???drawable??????????????????XGridBuilder??????
                .setColor(Color.BLUE)
                //??????????????????drawable,drawable>color
//                .setColorRes(R.color.white)
//                .setDrawable(new ColorDrawable(Color.WHITE))
//                .setDrawableRes(R.drawable.)
                .setHLineColor(Color.BLACK)
//                .setHLineDrawable()
                .setVLineColor(Color.RED)
//                .setVLineDrawable()
                //??????????????????
                .setIncludeEdge(true)
                //??????????????????????????????????????????????????????????????????????????????????????????????????????
                .setVerticalIncludeEdge(true)
                ;
    }

    /**
     * ??????????????????????????????????????????????????????
     *
     * @param recyclerView
     */
    public void deleteItemDivider(RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            recyclerView.removeItemDecoration(recyclerView.getItemDecorationAt(i));
        }
    }


    public static class GridAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public GridAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, String mS) {

        }
    }


    public List<String> getData() {
        return Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
    }

}