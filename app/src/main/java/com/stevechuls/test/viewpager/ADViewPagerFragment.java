package com.stevechuls.test.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.dataset.ImageViewItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ADViewPagerFragment extends Fragment {

    private ArrayList<ImageViewItem> arrayList;
    private Context context;

    public ADViewPagerFragment getInstance(Context context, ArrayList<ImageViewItem> arrayList)
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

        ImageView imageView = rootView.findViewById(R.id.fragment_image);

        Picasso.with(context)
                .load(arrayList.get(position).getImageUrl())
                .into(imageView);

        return rootView;
    }
}
