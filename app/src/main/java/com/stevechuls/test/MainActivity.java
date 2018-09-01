package com.stevechuls.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import com.stevechuls.test.fragment.FragmentViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button mainView_btn;
    private Button searchHospitalAround_btn;
    private Button searchPharmacyAround_btn;
    private Button searchHospitalSituation_btn;
    private Button showHospitalEvent_btn;

    private int currentPage;
    private int TOTAL_PAGE_COUNT = 5;
    private Timer timer;
    private ViewPager bottomViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //===========================MainActiviy 5개의 버튼=================================================
        // 굿닥 캐스트 버튼
        mainView_btn = findViewById(R.id.mainView_btn);
        mainView_btn.setOnClickListener(MainActivity.this);

        // 내 주변 병원 찾기 버튼
        searchHospitalAround_btn = findViewById(R.id.searchHospitalAround_btn);
        searchHospitalAround_btn.setOnClickListener(MainActivity.this);

        // 내 주변 약국 찾기 버트
        searchPharmacyAround_btn = findViewById(R.id.searchPharmacyAround_btn);
        searchPharmacyAround_btn.setOnClickListener(MainActivity.this);

        // 상황별 병원 찾기 버튼
        searchHospitalSituation_btn = findViewById(R.id.searchHospitalSituation_btn);
        searchHospitalSituation_btn.setOnClickListener(MainActivity.this);

        // 병원 이벤트 모아보기 버튼
        showHospitalEvent_btn = findViewById(R.id.showHospitalEvent_btn);
        showHospitalEvent_btn.setOnClickListener(MainActivity.this);

        //================================ViewPager=============================================
        // 메인액티비티 밑에 뷰페이저 초기화 및 뷰페이저 어댑터 세팅
        FragmentViewPagerAdapter fragmentViewPagerAdapter = new FragmentViewPagerAdapter(this, getSupportFragmentManager());

        bottomViewPager = findViewById(R.id.bottomViewPager);
        bottomViewPager.setAdapter(fragmentViewPagerAdapter);

        // 3초당 뷰페이저를 이동
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == TOTAL_PAGE_COUNT ) {
                    currentPage = 0;
                }
                bottomViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 3000);
        //====================================ViewPager=========================================
    }

    /*
        버튼 클릭 이벤트 처리
    */
    @Override
    public void onClick(View view) {

        if(view == mainView_btn) // 굿닥 캐스트 버튼 클릭 시, MainViewActivity로 이동
        {
            Intent intent = new Intent(this, MainViewActivity.class);
            startActivity(intent);
        }
        else if(view == searchHospitalAround_btn) // 내 주변 병원 찾기 버튼 클릭 시, MapActivity로 이동
        {
            Intent intent = new Intent(this, SearchHospitalAroundActivity.class);
            startActivity(intent);
        }
        else if(view == searchPharmacyAround_btn ||
                view == searchHospitalSituation_btn ||
                view == showHospitalEvent_btn) // 내 주변 약국 찾기, 상황별 병원 찾기, 병원이벤트 모아보기 버튼 클릭 시, 준비중
        {
            Toast.makeText(this, "준비중...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(timer == null)
        {
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == TOTAL_PAGE_COUNT ) {
                        currentPage = 0;
                    }
                    bottomViewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer= new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 0, 3300);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        currentPage = 0;
        timer.cancel();
        timer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bottomViewPager.setCurrentItem(0, true);
    }


}
