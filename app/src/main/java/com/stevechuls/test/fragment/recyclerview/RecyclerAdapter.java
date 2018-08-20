package com.stevechuls.test.fragment.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.stevechuls.test.R;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<RecyclerItem> recyclerItemArrayList;

    public RecyclerAdapter(Context context, ArrayList<RecyclerItem> recyclerItemArrayList)
    {
        this.recyclerItemArrayList = recyclerItemArrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new RecyclerViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.imageView.setImageDrawable(recyclerItemArrayList.get(position).getDrawable());
        holder.textView.setText(recyclerItemArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return recyclerItemArrayList.size();
    }
}
