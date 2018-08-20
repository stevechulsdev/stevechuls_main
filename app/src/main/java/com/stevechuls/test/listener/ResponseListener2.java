package com.stevechuls.test.listener;

import com.stevechuls.test.MapData;
import com.stevechuls.test.network.JsonItem2;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public interface ResponseListener2 {
    public void onSuccess(JsonItem2[] jsonItem);
    public void onFail(Throwable t);
}
