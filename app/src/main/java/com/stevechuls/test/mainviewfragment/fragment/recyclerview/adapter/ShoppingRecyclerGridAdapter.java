package com.stevechuls.test.mainviewfragment.fragment.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.ShoppingRecyclerGridItem;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder.ShoppingGridRecyclerViewHolder;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ShoppingRecyclerGridAdapter extends RecyclerView.Adapter<ShoppingGridRecyclerViewHolder> {

    private ArrayList<ShoppingRecyclerGridItem> shoppingRecyclerGridItemArrayList;

    public ShoppingRecyclerGridAdapter(Context context, ArrayList<ShoppingRecyclerGridItem> shoppingRecyclerGridItemArrayList)
    {
        this.shoppingRecyclerGridItemArrayList = shoppingRecyclerGridItemArrayList;
    }

    @Override
    public ShoppingGridRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new ShoppingGridRecyclerViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ShoppingGridRecyclerViewHolder holder, int position) {
        holder.imageView.setImageDrawable(shoppingRecyclerGridItemArrayList.get(position).getDrawable());
        holder.textView.setText(shoppingRecyclerGridItemArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return shoppingRecyclerGridItemArrayList.size();
    }
}
