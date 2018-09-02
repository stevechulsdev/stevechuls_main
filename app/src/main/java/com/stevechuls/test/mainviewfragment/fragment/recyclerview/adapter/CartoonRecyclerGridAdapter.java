package com.stevechuls.test.mainviewfragment.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.CarRecyclerGridItem;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder.CartoonRecyclerGridViewHolder;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class CartoonRecyclerGridAdapter extends RecyclerView.Adapter<CartoonRecyclerGridViewHolder> {

    private ArrayList<CarRecyclerGridItem> recyclerItemArrayList;
    private Context context;

    public CartoonRecyclerGridAdapter(Context context, ArrayList<CarRecyclerGridItem> recyclerItemArrayList)
    {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
    }

    @Override
    public CartoonRecyclerGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item2, null);
        return new CartoonRecyclerGridViewHolder(convertView, recyclerItemArrayList);
    }

    @Override
    public void onBindViewHolder(CartoonRecyclerGridViewHolder holder, int position) {
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
