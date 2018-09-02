package com.stevechuls.test.mainviewfragment.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.CartoonRecyclerGridItem;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder.CarRecyclerGridViewHolder;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class CarRecyclerGridAdapter extends RecyclerView.Adapter<CarRecyclerGridViewHolder> {

    private ArrayList<CartoonRecyclerGridItem> recyclerItemArrayList;
    private Context context;

    public CarRecyclerGridAdapter(Context context, ArrayList<CartoonRecyclerGridItem> recyclerItemArrayList)
    {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
    }

    @Override
    public CarRecyclerGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item3, null);
        return new CarRecyclerGridViewHolder(convertView, recyclerItemArrayList);
    }

    @Override
    public void onBindViewHolder(CarRecyclerGridViewHolder holder, int position) {
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
