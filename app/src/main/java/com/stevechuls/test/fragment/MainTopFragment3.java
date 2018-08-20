package com.stevechuls.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.fragment.imageview.ImageViewItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class MainTopFragment3 extends Fragment {

    private ArrayList<ImageViewItem> arrayList;

    public MainTopFragment3 getInstance(ArrayList<ImageViewItem> arrayList)
    {
        this.arrayList = arrayList;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        int position = bundle.getInt("position");

        View rootView = inflater.inflate(R.layout.fragment_image_view3, container, false);

        Log.e("ksc", "MainTopFragment3 onCreateView position : " + position);

        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.fragment_image3);
        imageView3.setImageDrawable(arrayList.get(position).getDrawable());

        return rootView;
    }
}
