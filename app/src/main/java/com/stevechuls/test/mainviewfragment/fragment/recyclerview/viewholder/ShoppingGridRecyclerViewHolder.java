package com.stevechuls.test.mainviewfragment.fragment.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.R;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ShoppingGridRecyclerViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public ImageView imageView;
    public TextView textView;

    public ShoppingGridRecyclerViewHolder(View itemView)
    {
        super(itemView);

        container = itemView.findViewById(R.id.itemView);
        imageView = itemView.findViewById(R.id.profilImageView);
        textView = itemView.findViewById(R.id.profileTextView);
    }
}
