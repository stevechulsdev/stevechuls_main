package com.stevechuls.test.dataset;

import com.google.gson.annotations.SerializedName;
import com.stevechuls.test.navermap.MapData;

import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public class JsonListItem {

    @SerializedName("items")
    public ArrayList<MapData> items;
}