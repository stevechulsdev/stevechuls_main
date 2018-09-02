package com.stevechuls.test.mainviewfragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.stevechuls.test.mainviewfragment.fragment.CarFragment;
import com.stevechuls.test.mainviewfragment.fragment.CartoonFragment;
import com.stevechuls.test.mainviewfragment.fragment.ShoppingFragment;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class MainViewFragmentAdapter extends FragmentPagerAdapter {

    private String TAG = "[stevechulsdev] MainViewFragmentAdapter";

    public MainViewFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    // 포지션 값에 따라 프래그먼트 페이지를 불러온다 ex) 0 = listview, 1 = gridview...
    @Override
    public Fragment getItem(int position) {

        Log.e(TAG, "gitItem position : " + position);

        if(position == 0)
        {
            Log.e(TAG, "ShoppingFragment position : " + position);
            return new ShoppingFragment();
//            return new CartoonFragment();
        }
        else if(position == 1)
        {
            Log.e(TAG, "CartoonFragment position : " + position);
            return new CartoonFragment();
        }
        else if(position == 2)
        {
            Log.e(TAG, "CarFragment position : " + position);
            return new CarFragment();
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
