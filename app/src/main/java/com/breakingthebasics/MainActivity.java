package com.breakingthebasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.breakingthebasics.fragments.PositiveFragment;
import com.breakingthebasics.fragments.PracticeFragment;
import com.breakingthebasics.fragments.ProfileFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linPrac,linPosiVibe,linProfile;
    private ImageView imgPrac,imgPosi,imgProfile;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findid();

        if (TextUtils.isEmpty(getString(R.string.banner_home_footer))) {
//            Toast.makeText(getApplicationContext(), "Please mention your Banner Ad ID in strings.xml", Toast.LENGTH_LONG).show();
            return;
        }

        mAdView = (AdView) findViewById(R.id.adView);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId(getString(R.string.banner_home_footer));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(getApplicationContext(), "Ad is load!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
//                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
//                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void findid(){

        linPrac=findViewById(R.id.linPrac);
        linPosiVibe=findViewById(R.id.linPosiVibe);
        linProfile=findViewById(R.id.linProfile);

        imgPrac=findViewById(R.id.imgPrac);
        imgPosi=findViewById(R.id.imgPosi);
        imgProfile=findViewById(R.id.imgProfile);
        linPrac.setOnClickListener(MainActivity.this);
        linPosiVibe.setOnClickListener(MainActivity.this);
        linProfile.setOnClickListener(MainActivity.this);


        //default Page
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_container, new PracticeFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        unSelected();
        Fragment fragment=null;
        switch (v.getId()){
            case R.id.linPrac:

                fragment=new PracticeFragment();
                imgPrac.setImageResource(R.drawable.home_select);
                break;
            case R.id.linPosiVibe:
                fragment=new PositiveFragment();
                imgPosi.setImageResource(R.drawable.message_select);
                break;
            case R.id.linProfile:
                fragment=new ProfileFragment();
                imgProfile.setImageResource(R.drawable.profile_select);
                break;

            default:
                break;
        }

        if(fragment!=null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.main_container, fragment);
            transaction.commit();
        }
    }

    private void unSelected(){
        imgPrac.setImageResource(R.drawable.home_unselect);
        imgPosi.setImageResource(R.drawable.message_unselect);
        imgProfile.setImageResource(R.drawable.profile_unselect);
    }

}