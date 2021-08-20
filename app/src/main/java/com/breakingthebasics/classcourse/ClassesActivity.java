package com.breakingthebasics.classcourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.breakingthebasics.R;
import com.breakingthebasics.adapters.ClassesAdapter;
import com.breakingthebasics.adapters.CoursesAdapter;
import com.breakingthebasics.common.MyPref;
import com.breakingthebasics.common.RecyclerItemClickListener;
import com.breakingthebasics.model.Gson_category;

public class ClassesActivity extends AppCompatActivity {
    private Bundle bun;
    private String data = "";
    Gson_category.datares video_model = null;

    private RecyclerView recCource, recClasses;
    private TextView txtCourses, txtClasses, tvTitle;
    private Context context;
    private ImageView imgBack;
    CoursesAdapter adapter_recyclerview=null;
    MyPref myPref;
RelativeLayout relHeader;
    public void setMarginTop() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                relHeader.getLayoutParams()
        );
        params.setMargins(0, myPref.getstatusHeight(), 0, 0);
        relHeader.setLayoutParams(params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        context = ClassesActivity.this;
        myPref=new MyPref(context);
        relHeader=findViewById(R.id.relHeader);
        setMarginTop();


        recCource = findViewById(R.id.recCource);
        txtCourses = findViewById(R.id.txtCourses);
        txtClasses = findViewById(R.id.txtClasses);
        recClasses = findViewById(R.id.recClasses);

        recClasses.setHasFixedSize(true);
        recCource.setHasFixedSize(true);

        tvTitle = findViewById(R.id.tvTitle);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bun = getIntent().getExtras();
        if (bun.containsKey("data")) {
            data = bun.getString("data");
            if (data != null) {
                Gson gson = new Gson();
                video_model = gson.fromJson(data, Gson_category.datares.class);
                tvTitle.setText(video_model.getName());
            }
        }

        if (video_model != null) {
            if (video_model.getClasses() != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                recClasses.setLayoutManager(linearLayoutManager);
                recClasses.setLayoutManager(new GridLayoutManager(this, 2));
                ClassesAdapter adapter_recyclerview = new ClassesAdapter(context, video_model.getClasses());
                recClasses.setAdapter(adapter_recyclerview);

                recClasses.addOnItemTouchListener(new RecyclerItemClickListener(ClassesActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(video_model.getClasses().get(position));

                            Intent in = new Intent(ClassesActivity.this, ClassesDetailActivity.class);
                            in.putExtra("data", jsonString);
                            startActivity(in);


                    }
                }));

                if (video_model.getClasses().size() == 0) {
                    txtClasses.setVisibility(View.GONE);
                    recClasses.setVisibility(View.GONE);
                }

            } else {
                txtClasses.setVisibility(View.GONE);
            }

            if (video_model.getCourse() != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                recCource.setLayoutManager(linearLayoutManager);
                 adapter_recyclerview = new CoursesAdapter(context, video_model.getCourse());
                recCource.setAdapter(adapter_recyclerview);

                recCource.addOnItemTouchListener(new RecyclerItemClickListener(ClassesActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(video_model.getCourse().get(position));

                        Intent in = new Intent(ClassesActivity.this, CoursesDetailActivity.class);
                        in.putExtra("data", jsonString);
                        startActivity(in);

                    }
                }));


                if (video_model.getCourse().size() == 0) {
                    txtCourses.setVisibility(View.GONE);
                    recCource.setVisibility(View.GONE);
                }

            } else {
                txtCourses.setVisibility(View.GONE);
            }
        } else {
            txtClasses.setVisibility(View.GONE);
            txtCourses.setVisibility(View.GONE);
        }
        recCource.setNestedScrollingEnabled(false);
        recClasses.setNestedScrollingEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        adapter_recyclerview
//        isBreak
    }
}