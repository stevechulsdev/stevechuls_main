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

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridViewHolder> {

    private ArrayList<RecyclerGridItem2> recyclerItemArrayList;
    private Context context;

    public RecyclerGridAdapter(Context context, ArrayList<RecyclerGridItem2> recyclerItemArrayList)
    {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
    }

    @Override
    public RecyclerGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item2, null);
        return new RecyclerGridViewHolder(convertView, recyclerItemArrayList);
    }

    @Override
    public void onBindViewHolder(RecyclerGridViewHolder holder, int position) {
        String url = "http://sangchul.ipdisk.co.kr:7364/image/cartoon/";

        Picasso.with(context)
                .load(url + recyclerItemArrayList.get(position).getImageUrl())
                .into(holder.imageView2);
        holder.imageView2.getDrawable();

        holder.textView2.setText(recyclerItemArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return recyclerItemArrayList.size();
    }
}
