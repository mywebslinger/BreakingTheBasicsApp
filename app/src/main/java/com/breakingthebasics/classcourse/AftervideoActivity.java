package com.breakingthebasics.classcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.breakingthebasics.R;
import com.breakingthebasics.common.MyPref;

public class AftervideoActivity extends AppCompatActivity {
    TextView tvContinue;
    private ImageView imgSetting;
    private MyPref myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftervideo);
        tvContinue = findViewById(R.id.tvContinue);
        myPref = new MyPref(AftervideoActivity.this);
        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgSetting = findViewById(R.id.imgSetting);
        setMarginTop();
    }


    public void setMarginTop() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                imgSetting.getLayoutParams()
        );

        params.setMargins(0, myPref.getstatusHeight(), 0, 0);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imgSetting.setLayoutParams(params);
    }

}