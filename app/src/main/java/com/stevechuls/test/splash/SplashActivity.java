package com.stevechuls.test.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.stevechuls.test.MainActivity;
import com.stevechuls.test.R;

/**
 * 앱 실행시에 SplashImage를 띄우기 위한 Activity 입니다.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_splash에서 splash의 배경색과 이미지 설정을 합니다.
        setContentView(R.layout.activity_splash);

        // postDelayed를 사용해서 3초 동안 SplashImage를 보여주고, MainActivty로 이동합니다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        }, 3000);

    }
}
