package com.stevechuls.test.listener;

import com.stevechuls.test.dataset.JsonItem;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public interface ResponseListener {
    public void onSuccess(JsonItem[] jsonItem);
    public void onFail(Throwable t);
}
