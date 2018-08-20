package com.stevechuls.test.fragment.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stevechuls.test.DetailActivity;
import com.stevechuls.test.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class RecyclerGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public LinearLayout container2;
    public ImageView imageView2;
    public TextView textView2;
    public ArrayList<RecyclerGridItem2> recyclerGridItem2ArrayList;

    public RecyclerGridViewHolder(View itemView, ArrayList<RecyclerGridItem2> recyclerGridItem2ArrayList)
    {
        super(itemView);

        this.recyclerGridItem2ArrayList = recyclerGridItem2ArrayList;

        itemView.setOnClickListener(this);
        container2 = (LinearLayout)itemView.findViewById(R.id.itemView2);
        imageView2 = (ImageView)itemView.findViewById(R.id.profilImageView2);
        textView2 = (TextView)itemView.findViewById(R.id.profileTextView2);
    }

    @Override
    public void onClick(View view) {

        Log.e("ksc", "====================================position : "+recyclerGridItem2ArrayList.get(getAdapterPosition()));
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        Log.e("ksc", "====================================imageurl : "+recyclerGridItem2ArrayList.get(getAdapterPosition()).getImageUrl());
//        Bitmap bitmap =  ((BitmapDrawable)recyclerGridItem2ArrayList.get(getAdapterPosition()).getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] bytes = stream.toByteArray();
//        intent.putExtra("bitmap", bytes);
//        intent.putExtra("bitmap", bitmap);
        intent.putExtra("imageurl", recyclerGridItem2ArrayList.get(getAdapterPosition()).getImageUrl());
        view.getContext().startActivity(intent);
    }
}
