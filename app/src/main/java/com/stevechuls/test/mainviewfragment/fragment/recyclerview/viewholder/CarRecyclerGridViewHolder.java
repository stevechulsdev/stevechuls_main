package com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.mainviewfragment.DetailActivity;
import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.CartoonRecyclerGridItem;

import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class CarRecyclerGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public LinearLayout container3;
    public ImageView imageView3;
    public TextView textView3;
    public ArrayList<CartoonRecyclerGridItem> cartoonRecyclerGridItem3ArrayList;

    public CarRecyclerGridViewHolder(View itemView, ArrayList<CartoonRecyclerGridItem> cartoonRecyclerGridItem3ArrayList)
    {
        super(itemView);

        this.cartoonRecyclerGridItem3ArrayList = cartoonRecyclerGridItem3ArrayList;

        itemView.setOnClickListener(this);
        container3 = itemView.findViewById(R.id.itemView3);
        imageView3 = itemView.findViewById(R.id.profilImageView3);
        textView3 = itemView.findViewById(R.id.profileTextView3);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("imageurl", cartoonRecyclerGridItem3ArrayList.get(getAdapterPosition()).getImageUrl());
        intent.putExtra("imagepath", "http://sangchul.ipdisk.co.kr:7364/image/car/");
        view.getContext().startActivity(intent);
    }
}
