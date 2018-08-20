package com.stevechuls.test.listener;

import com.stevechuls.test.MapData;
import com.stevechuls.test.network.JsonListItem;

/**
 * Created by entermate_ksc on 2018. 4. 16..
 */

public interface MapDataResponseListener {
    public void onMapDataSuccess(String mapData);
}
