package com.breakingthebasics.settingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.breakingthebasics.R;
import com.breakingthebasics.common.Apiclass;
import com.breakingthebasics.common.ClassRetrofit;
import com.breakingthebasics.common.MyProgress;
import com.breakingthebasics.model.GsonTerm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermConditionActivity extends AppCompatActivity {
    private ClassRetrofit classRetrofit;
    private Context context;
    private WebView myWeb;
    private ImageView imgBack;
    private MyProgress myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_condition);

        context= TermConditionActivity.this;
        classRetrofit = new ClassRetrofit(context);
        myProgress=new MyProgress(context);
        myWeb=findViewById(R.id.myWeb);
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myProgress.showLoading("Wait...");
        setData();
        new PageConnection().execute();
    }
    public void setData(){
        WebSettings webSettings = myWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    private class PageConnection extends AsyncTask<Void, Void, Void> {
        Apiclass service;
        @Override
        protected Void doInBackground(Void... voids) {
            service = classRetrofit.myresponse(Apiclass.url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            service.getpage("terms").enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String myres = response.body().toString();
                    myProgress.hideLoading();
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        GsonTerm gsonTerm = gson.fromJson(myres, GsonTerm.class);
                        if (gsonTerm.getStatus().equalsIgnoreCase("true")) {
                            if(gsonTerm.getData().size()>0) {
                                String unencodedHtml = "" + gsonTerm.getData().get(0).getContent();
                                String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
                                myWeb.loadData(encodedHtml, "text/html", "base64");
                            }
                        } else {
                            Toast.makeText(context, "" + gsonTerm.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    myProgress.hideLoading();
                }
            });
        }
    }
}