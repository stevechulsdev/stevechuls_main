package com.stevechuls.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.fragment.imageview.ImageViewItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class MainTopFragment2 extends Fragment {

    private ArrayList<ImageViewItem> arrayList;

    public MainTopFragment2 getInstance(ArrayList<ImageViewItem> arrayList)
    {
        this.arrayList = arrayList;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        View rootView = inflater.inflate(R.layout.fragment_image_view2, container, false);

        Log.e("ksc", "MainTopFragment22222222222222222222222222222222 onCreateView position imageview : " + position);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.fragment_image2);

        Picasso.with(getContext())
                .load("http://sangchul.ipdisk.co.kr:7364/image/banner/banner_01.png")
                .into(imageView);

//        imageView.setImageDrawable(arrayList.get(position).getDrawable());

        return rootView;
    }
}
