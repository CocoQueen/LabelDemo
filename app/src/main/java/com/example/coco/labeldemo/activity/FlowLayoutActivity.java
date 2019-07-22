package com.example.coco.labeldemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coco.labeldemo.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlowLayoutActivity extends AppCompatActivity {

    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.tv_selected)
    TextView tvSelected;
    @BindView(R.id.flowlayout_selected)
    TagFlowLayout flowlayoutSelected;

    private Context context;
    private String[] mVals = new String[]
            {"感染性废物", "病理性废物", "化学性废物", "损伤性废物", "药物性废物", "医用固体垃圾"};
    private int maxSelected = 1;
    private Set<Integer> selectedList;
    private String[] mValsSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        ButterKnife.bind(this);
        context = this;

        initFlowLayout();
        initFlowLayoutSelected();
    }

    /**
     * 初始化所有标签的FlowLayout
     */
    private void initFlowLayout() {
        TagAdapter tagAdapter = new TagAdapter(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                TextView view = (TextView) View.inflate(context, R.layout.flowlayout_textview_selected, null);
                view.setText(mVals[position]);
                return view;
            }
        };
        //预先设置选中
//        tagAdapter.setSelectedList(0, 1);
        flowlayout.setAdapter(tagAdapter);

        //设置最大选中数
        flowlayout.setMaxSelectCount(maxSelected);

        //为FlowLayout的标签设置监听事件
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (selectedList.size() >= maxSelected) {
                    Toast.makeText(context, "已达最大选中数" + maxSelected, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, mVals[position], Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //为FlowLayout的标签设置选中监听事件
        flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                initFlowLayoutSelected();
            }
        });
    }

    /**
     * 初始化选中标签的FlowLayout
     */
    private void initFlowLayoutSelected() {
        int i = 0;
        //获得所有选中的position集合,例如[1,2,3,4]
        selectedList = flowlayout.getSelectedList();
        mValsSelected = new String[selectedList.size()];
        Iterator<Integer> iterator = selectedList.iterator();
        while (iterator.hasNext()) {
            mValsSelected[i] = mVals[iterator.next()];
            i++;
        }

        tvSelected.setText("最大选中数为：" + maxSelected + "（已选中" + selectedList.size() + ")" + "(position:" + selectedList.toString() + ")");
        flowlayoutSelected.setAdapter(new TagAdapter(mValsSelected) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                TextView view = (TextView) View.inflate(context, R.layout.flowlayout_textview_no_selected, null);
                view.setText(mValsSelected[position]);
                return view;
            }
        });
    }
}
