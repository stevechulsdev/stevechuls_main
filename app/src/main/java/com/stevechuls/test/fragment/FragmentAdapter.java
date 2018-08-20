package com.stevechuls.test.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.stevechuls.test.R;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    // 포지션 값에 따라 프래그먼트 페이지를 불러온다 ex) 0 = listview, 1 = gridview...
    @Override
    public Fragment getItem(int position) {

        Log.e("ksc", "FragmentAdapter gitItem position : " + position);

        if(position == 0)
        {
            Log.e("ksc", "MainFragment position : " + position);
            return new MainFragment();
//            return new MainFragment2();
        }
        else if(position == 1)
        {
            Log.e("ksc", "MainFragment2 position : " + position);
            return new MainFragment2();
        }
        else if(position == 2)
        {
            Log.e("ksc", "MainFragment3 position : " + position);
            return new MainFragment3();
        }
        else
        {
            return null;
        }


    }

    // 뷰페이져에 올라간 프래그먼트의 갯수
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
