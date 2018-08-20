package com.stevechuls.test.fragment;

import android.content.Context;
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

public class MainTopFragment extends Fragment {

    private ArrayList<ImageViewItem> arrayList;
    private Context context;

    public MainTopFragment getInstance(Context context, ArrayList<ImageViewItem> arrayList)
    {
        this.arrayList = arrayList;
        this.context = context;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        View rootView = inflater.inflate(R.layout.fragment_image_view, container, false);

        Log.e("ksc", "MainTopFragment111111111111111111111111111 onCreateView position imageview : " + position);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.fragment_image);


//        for(int i=1; i<=5; i++)
//        {
//            Picasso.with(getContext())
//                    .load("http://sangchul.ipdisk.co.kr:8000/image/banner/banner_0"+i+".png")
//                    .into(imageView);
//        }

        Picasso.with(context)
                .load(arrayList.get(position).getImageUrl())
                .into(imageView);
//        imageView.setImageDrawable(arrayList.get(position).getDrawable());

        return rootView;
    }
}
