package com.stevechuls.test.network;

import com.google.gson.annotations.SerializedName;
import com.stevechuls.test.MapData;

import java.util.ArrayList;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public class JsonListItem {

    @SerializedName("items")
    public ArrayList<MapData> items;
}
