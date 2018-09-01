package com.stevechuls.test.fragment.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.DetailActivity;
import com.stevechuls.test.R;

import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class RecyclerGridViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener{

    public LinearLayout container3;
    public ImageView imageView3;
    public TextView textView3;
    public ArrayList<RecyclerGridItem> recyclerGridItem3ArrayList;

    public RecyclerGridViewHolder3(View itemView, ArrayList<RecyclerGridItem> recyclerGridItem3ArrayList)
    {
        super(itemView);

        this.recyclerGridItem3ArrayList = recyclerGridItem3ArrayList;

        itemView.setOnClickListener(this);
        container3 = (LinearLayout)itemView.findViewById(R.id.itemView3);
        imageView3 = (ImageView)itemView.findViewById(R.id.profilImageView3);
        textView3 = (TextView)itemView.findViewById(R.id.profileTextView3);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("imageurl", recyclerGridItem3ArrayList.get(getAdapterPosition()).getImageUrl());
        view.getContext().startActivity(intent);
    }
}
