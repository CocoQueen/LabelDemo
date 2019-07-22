package com.example.coco.labeldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.coco.labeldemo.activity.FlowLayoutActivity;
import com.example.coco.labeldemo.activity.LabelLinkFlowLayoutActivity;
import com.example.coco.labeldemo.activity.LabelLinkRecyclerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bt_gridview)
    Button btGridview;
    @BindView(R.id.bt_flowLayout)
    Button btFlowLayout;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_gridview, R.id.bt_flowLayout, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_gridview:
                startActivity(new Intent(getApplicationContext(), LabelLinkRecyclerActivity.class));
                break;
            case R.id.bt_flowLayout:
                startActivity(new Intent(getApplicationContext(), LabelLinkFlowLayoutActivity.class));
                break;
            case R.id.btn:
                startActivity(new Intent(getApplicationContext(), FlowLayoutActivity.class));
                break;
        }
    }
}
