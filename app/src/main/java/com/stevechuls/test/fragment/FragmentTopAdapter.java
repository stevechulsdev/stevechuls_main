package com.stevechuls.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.fragment.imageview.ImageViewItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class FragmentTopAdapter extends FragmentPagerAdapter {

    private MainTopFragment mainTopFragment;
    private MainTopFragment2 mainTopFragment2;
    private MainTopFragment3 mainTopFragment3;
    private Context context;
    private ArrayList<ImageViewItem> arrayList;

    public FragmentTopAdapter(Context context, FragmentManager fm) {
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

//        ArrayList<ImageViewItem> arrayList = new ArrayList<>();
//        for(int i=1; i<=3; i++) {
//            if (i == 1) {
//                ImageViewItem imageViewItem = new ImageViewItem();
//                imageViewItem.setDrawable(ContextCompat.getDrawable(context, R.drawable.image_01));
//                arrayList.add(imageViewItem);
//            } else if (i == 2) {
//                ImageViewItem imageViewItem = new ImageViewItem();
//                imageViewItem.setDrawable(ContextCompat.getDrawable(context, R.drawable.image_02));
//                arrayList.add(imageViewItem);
//            } else if (i == 3) {
//                ImageViewItem imageViewItem = new ImageViewItem();
//                imageViewItem.setDrawable(ContextCompat.getDrawable(context, R.drawable.image_03));
//                arrayList.add(imageViewItem);
//            } else {
//
//            }
//        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        Log.e("ksc", "FragmentTopAdapter position : " + position);

//        if(position == 0)
//        {
            Log.e("ksc", "MainTopFragment position : " + position);
            mainTopFragment = new MainTopFragment().getInstance(context, arrayList);
            mainTopFragment.setArguments(bundle);
            return mainTopFragment;
//        }
//        else if(position == 1)
//        {
//            Log.e("ksc", "MainTopFragment2 position : " + position);
//            mainTopFragment2 = new MainTopFragment2().getInstance(arrayList);
//            mainTopFragment2.setArguments(bundle);
//            return mainTopFragment2;
//        }
//        else if(position == 2)
//        {
//            Log.e("ksc", "MainTopFragment3 position : " + position);
//            mainTopFragment3 = new MainTopFragment3().getInstance(arrayList);
//            mainTopFragment3.setArguments(bundle);
//            return mainTopFragment3;
//        }
//        else
//        {
//            return null;
//        }
    }

    // 뷰페이져에 올라간 프래그먼트의 갯수
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 5;
    }
}
