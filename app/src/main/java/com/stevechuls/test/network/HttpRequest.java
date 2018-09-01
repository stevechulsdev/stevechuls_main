package com.stevechuls.test.network;

import android.util.Log;

import com.stevechuls.test.MapData;
import com.stevechuls.test.listener.MapDataResponseListener;
import com.stevechuls.test.listener.ResponseListener;
import com.stevechuls.test.listener.ResponseListener2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public class HttpRequest {

    public void callAPI(final ResponseListener listener)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sangchul.ipdisk.co.kr:7364/").addConverterFactory(GsonConverterFactory.create()).build();
        ListAPI listAPI = retrofit.create(ListAPI.class);
        Call<JsonItem[]> mInfo = listAPI.getInfo();
        mInfo.enqueue(new Callback<JsonItem[]>() {
            @Override
            public void onResponse(Call<JsonItem[]> call, Response<JsonItem[]> response) {
                if(response != null && response.isSuccessful() && response.body() != null)
                {
                    JsonItem[] jsonItem = response.body();
                    listener.onSuccess(jsonItem);
                }
            }

            @Override
            public void onFailure(Call<JsonItem[]> call, Throwable t) {
                Log.e("ksc", "api call error : " + t.toString());
                listener.onFail(t);
            }
        });
    }

    public void callCarImageAPI(final ResponseListener listener)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sangchul.ipdisk.co.kr:7364/").addConverterFactory(GsonConverterFactory.create()).build();
        ListAPI listAPI = retrofit.create(ListAPI.class);
        Call<JsonItem[]> mInfo = listAPI.getCarImageInfo();
        mInfo.enqueue(new Callback<JsonItem[]>() {
            @Override
            public void onResponse(Call<JsonItem[]> call, Response<JsonItem[]> response) {
                if(response != null && response.isSuccessful() && response.body() != null)
                {
                    JsonItem[] jsonItem = response.body();
                    listener.onSuccess(jsonItem);
                }
            }

            @Override
            public void onFailure(Call<JsonItem[]> call, Throwable t) {
                Log.e("ksc", "api call error : " + t.toString());
                listener.onFail(t);
            }
        });
    }

    public void callCartoonImageAPI(final ResponseListener2 listener)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sangchul.ipdisk.co.kr:7364/").addConverterFactory(GsonConverterFactory.create()).build();
        ListAPI listAPI = retrofit.create(ListAPI.class);
        Call<JsonItem2[]> mInfo = listAPI.getCartoonImageInfo();
        mInfo.enqueue(new Callback<JsonItem2[]>() {
            @Override
            public void onResponse(Call<JsonItem2[]> call, Response<JsonItem2[]> response) {
                if(response != null && response.isSuccessful() && response.body() != null)
                {
                    JsonItem2[] jsonItem = response.body();
                    listener.onSuccess(jsonItem);
                }
            }

            @Override
            public void onFailure(Call<JsonItem2[]> call, Throwable t) {
                Log.e("ksc", "api call error : " + t.toString());
                listener.onFail(t);
            }
        });
    }

    public void callMapDataAPI(String addr, final MapDataResponseListener listener)
    {
//        String mAddr;
//        mAddr = addr;
//
//        try {
//            mAddr = URLEncoder.encode(addr, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/v1/map/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ListAPI listAPI = retrofit.create(ListAPI.class);
        Call<ResponseBody> mInfo = listAPI.getCallMapDataAPI(addr);
        mInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response != null && response.isSuccessful() && response.body() != null)
                {
                    String jsonItem = null;
                    try {
                        jsonItem = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listener.onMapDataSuccess(jsonItem);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ksc", "api call error : " + t.toString());
//                listener.onFail(t);
            }
        });
    }
}
