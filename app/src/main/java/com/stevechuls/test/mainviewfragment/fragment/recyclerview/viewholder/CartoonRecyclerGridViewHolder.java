package com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.mainviewfragment.DetailActivity;
import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.CarRecyclerGridItem;

import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class CartoonRecyclerGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public LinearLayout container2;
    public ImageView imageView2;
    public TextView textView2;
    public ArrayList<CarRecyclerGridItem> carRecyclerGridItemArrayList;

    public CartoonRecyclerGridViewHolder(View itemView, ArrayList<CarRecyclerGridItem> carRecyclerGridItemArrayList)
    {
        super(itemView);

        this.carRecyclerGridItemArrayList = carRecyclerGridItemArrayList;

        itemView.setOnClickListener(this);
        container2 = itemView.findViewById(R.id.itemView2);
        imageView2 = itemView.findViewById(R.id.profilImageView2);
        textView2 = itemView.findViewById(R.id.profileTextView2);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("imageurl", carRecyclerGridItemArrayList.get(getAdapterPosition()).getImageUrl());
        intent.putExtra("imagepath", "http://sangchul.ipdisk.co.kr:7364/image/cartoon/");
        view.getContext().startActivity(intent);
    }
}
