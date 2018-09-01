package com.stevechuls.test.fragment.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.stevechuls.test.R;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class RecyclerGridAdapter3 extends RecyclerView.Adapter<RecyclerGridViewHolder3> {

    private ArrayList<RecyclerGridItem> recyclerItemArrayList;
    private Context context;

    public RecyclerGridAdapter3(Context context, ArrayList<RecyclerGridItem> recyclerItemArrayList)
    {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
    }

    @Override
    public RecyclerGridViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item3, null);
        return new RecyclerGridViewHolder3(convertView, recyclerItemArrayList);
    }

    @Override
    public void onBindViewHolder(RecyclerGridViewHolder3 holder, int position) {
        String url = "http://sangchul.ipdisk.co.kr:7364/image/car/";

        Picasso.with(context)
                .load(url + recyclerItemArrayList.get(position).getImageUrl())
                .into(holder.imageView3);

        holder.textView3.setText(recyclerItemArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return recyclerItemArrayList.size();
    }
}
