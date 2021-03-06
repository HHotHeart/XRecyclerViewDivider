package com.littlejerk.xrecyclerviewdivider;

import android.os.Bundle;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.littlejerk.rvdivider.builder.XLinearBuilder;
import com.littlejerk.rvdivider.decoration.LDecoration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinearActivity extends AppCompatActivity {

    private LinearAdapter mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        findViewById(R.id.iv_back).setOnClickListener(mView -> onBackPressed());
        RadioButton rb_v = findViewById(R.id.rb_v);
        RadioButton rb_h = findViewById(R.id.rb_h);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        rb_v.setOnCheckedChangeListener((mCompoundButton, mB) -> {
            if (mB) {
                deleteItemDivider(recyclerView);
                mManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mManager);
                recyclerView.addItemDecoration(bindXLinear().build());
                mAdapter = new LinearAdapter(R.layout.item_linear_vertical_list, getData());
                recyclerView.setAdapter(mAdapter);
            }
        });
        rb_h.setOnCheckedChangeListener((mCompoundButton, mB) -> {
            if (mB) {
                deleteItemDivider(recyclerView);
                mManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
                recyclerView.setLayoutManager(mManager);
                recyclerView.addItemDecoration(bindXLinear().build());
                mAdapter = new LinearAdapter(R.layout.item_linear_horizontal_list, getData());
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
    public XLinearBuilder bindXLinear() {
        return new XLinearBuilder(this)
                //????????????????????????float dp ???DimenRes dp
                .setSpacing(2f)
                .setShowFirstTopLine(true)
                .setShowLastLine(true)
//                .setColor(Color.BLACK)
                //??????????????????drawable,drawable>color
//                .setColorRes(R.color.white)
//                .setDrawable(new ColorDrawable(Color.WHITE))
//                .setDrawableRes(R.drawable.)
                //????????????RecyclerView???padding????????????????????????????????????????????????
                .setIncludeParentHVPadding(false, false)
                //??????item????????????padding????????????????????????????????????????????????
                .setPadding(4f)
//                .setLeftPadding(4f)
//                .setRightPadding(4f)
//                .setTopPadding(4f)
//                .setBottomPadding(4f)
                //???????????????position?????????????????????????????????new int[]{0,1}?????????null???????????????
                .setOnItemNoDivider(() -> null)
                //????????????item???????????????????????????????????????????????????????????????null???????????????
                .setOnItemDividerDecoration(new XLinearBuilder.OnItemDivider() {
                    @Override
                    public LDecoration getItemDividerDecoration(int position) {
//                        if(position == 1){
//                            return new LDecoration(LinearActivity.this)
//                                    //??????
//                                    .setAroundEdge(true,null,true,null)
//                                    //??????
//                                    .setAroundEdge(null,true,null,true)
//
//                                    //??????drawable?????????????????????????????????????????????
//                                    .setColor(Color.RED)
//                                    //??????padding???????????????
//                                    .setPadding(10f);
//
//                        }
                        return null;
                    }
                })
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


    public static class LinearAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LinearAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, String mS) {

        }
    }


    public List<String> getData() {
        return Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
    }


}