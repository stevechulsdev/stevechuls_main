package com.stevechuls.test.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import com.stevechuls.test.dataset.ImageViewItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ADViewPagerAdapter extends FragmentPagerAdapter {

    private String TAG = "[stevechulsdev] ADViewPagerAdapter";
    private ADViewPagerFragment ADViewPagerFragment;
    private Context context;
    private ArrayList<ImageViewItem> arrayList;

    public ADViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);

        this.context = context;
    }


    // 포지션 값에 따라 프래그먼트 페이지를 불러온다 ex) 0 = listview, 1 = gridview...
    @Override
    public Fragment getItem(int position) {

        arrayList = new ArrayList<>();
        for(int i=1; i<=5; i++)
        {
            ImageViewItem recyclerItem = new ImageViewItem();
            recyclerItem.setImageUrl("http://sangchul.ipdisk.co.kr:7364/image/banner/banner_0"+i+".png");
            arrayList.add(recyclerItem);
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        Log.e(TAG, "position : " + position);

            ADViewPagerFragment = new ADViewPagerFragment().getInstance(context, arrayList);
            ADViewPagerFragment.setArguments(bundle);
            return ADViewPagerFragment;
    }

    // 뷰페이져에 올라간 프래그먼트의 갯수
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 5;
    }
}
