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

import com.stevechuls.test.fragment.FragmentTopAdapter;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button mGridBtn;
    private Button mMapBtn;
    private Button mIngBtn1;
    private Button mIngBtn2;
    private int currentPage;
    private int TOT_PAGE_COUNT = 5;
    private Timer mTimer;
    private ViewPager mTopViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridBtn = (Button)findViewById(R.id.grid_btn);
        mGridBtn.setOnClickListener(MainActivity.this);

        mMapBtn = (Button)findViewById(R.id.map_btn);
        mMapBtn.setOnClickListener(MainActivity.this);

        mIngBtn1 = (Button)findViewById(R.id.ing_btn1);
        mIngBtn1.setOnClickListener(MainActivity.this);

        mIngBtn2 = (Button)findViewById(R.id.ing_btn2);
        mIngBtn2.setOnClickListener(MainActivity.this);

        FragmentTopAdapter fragmentTopAdapter = new FragmentTopAdapter(this, getSupportFragmentManager());

        mTopViewPager = (ViewPager)findViewById(R.id.container);
        mTopViewPager.setAdapter(fragmentTopAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == TOT_PAGE_COUNT ) {
                    currentPage = 0;
                }
                mTopViewPager.setCurrentItem(currentPage++, true);
            }
        };

        mTimer= new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 550, 3300);
    }

    @Override
    public void onClick(View view) {

        if(view == mGridBtn)
        {
            Intent intent = new Intent(this, GridActivity.class);
            startActivity(intent);
        }
        else if(view == mMapBtn)
        {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
        else if(view == mIngBtn1 || view == mIngBtn2)
        {
            Toast.makeText(this, "준비중...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mTimer == null)
        {
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == TOT_PAGE_COUNT ) {
                        currentPage = 0;
                    }
                    mTopViewPager.setCurrentItem(currentPage++, true);
                }
            };

            mTimer= new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 550, 3300);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        currentPage = 0;
        mTimer.cancel();
        mTimer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTopViewPager.setCurrentItem(0, true);
    }


}
