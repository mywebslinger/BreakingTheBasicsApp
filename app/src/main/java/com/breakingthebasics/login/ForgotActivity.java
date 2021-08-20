package com.breakingthebasics.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.breakingthebasics.R;
import com.breakingthebasics.common.Apiclass;
import com.breakingthebasics.common.CheckNetwork;
import com.breakingthebasics.common.ClassRetrofit;
import com.breakingthebasics.common.MyPref;
import com.breakingthebasics.common.MyProgress;
import com.breakingthebasics.model.Gson_forgot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBack;
    private TextView tvSubmit;
    private EditText edtEmail;
    private CheckNetwork checkNetwork;
    private ClassRetrofit classRetrofit;
    private MyProgress myProgress;
    private Context context;
    private MyPref myPref;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",stremail = "";


    public void setMarginTop() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(getResources().getDimensionPixelSize(R.dimen.imgBack_marLeft), myPref.getstatusHeight(), 0, 0);
        imgBack.setLayoutParams(params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        context=ForgotActivity.this;
        myPref=new MyPref(context);
        classRetrofit = new ClassRetrofit(context);
        checkNetwork = new CheckNetwork(context);
        myProgress=new MyProgress(context);
        findid();
    }
    private void findid(){
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(ForgotActivity.this);
        setMarginTop();
        tvSubmit=findViewById(R.id.tvSubmit);
        tvSubmit.setOnClickListener(ForgotActivity.this);
        edtEmail=findViewById(R.id.edtEmail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSubmit:
                stremail=edtEmail.getText().toString();
                setForgot();
                break;
            default:
                break;
        }
    }

    private void setForgot() {
        String succ = checkvalidation();
        if (succ.equalsIgnoreCase("true")) {
            if (checkNetwork.NetworkAvailable()) {
                myProgress.showLoading("Wait...");
                new ForgotConnection().execute();
            } else {
                Toast.makeText(context, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String checkvalidation() {
        String succ = "false";
        if (!stremail.matches(emailPattern)) {
            edtEmail.setError("Enter valid gmail!");
        }  else {
            succ = "true";
        }

        return succ;
    }

    private class ForgotConnection extends AsyncTask<Void, Void, Void> {
        Apiclass service;
        @Override
        protected Void doInBackground(Void... voids) {
            service = classRetrofit.myresponse(Apiclass.url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            service.getforgotpassword(stremail).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String myres = response.body().toString();
                    myProgress.hideLoading();
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        Gson_forgot loginres = gson.fromJson(myres, Gson_forgot.class);

                        if (loginres.getStatus().equalsIgnoreCase("true")) {
                            Toast.makeText(context, "" + loginres.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(context, "" + loginres.getMessage(), Toast.LENGTH_SHORT).show();
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