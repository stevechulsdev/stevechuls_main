package com.stevechuls.test.fragment.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.R;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public ImageView imageView;
    public TextView textView;

    public RecyclerViewHolder(View itemView)
    {
        super(itemView);

        container = (LinearLayout)itemView.findViewById(R.id.itemView);
        imageView = (ImageView)itemView.findViewById(R.id.profilImageView);
        textView = (TextView)itemView.findViewById(R.id.profileTextView);
    }
}
