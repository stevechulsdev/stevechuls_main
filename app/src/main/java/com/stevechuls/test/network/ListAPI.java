package com.stevechuls.test.network;

import com.stevechuls.test.MapData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by entermate_ksc on 2018. 4. 12..
 */

public interface ListAPI {

//    @GET("select_gallery.php")
//    Call<JsonListItem>getInfo();

    @GET("select_gallery.php")
    Call<JsonItem[]>getInfo();

    @GET("select_carimage.php")
    Call<JsonItem[]>getCarImageInfo();

    @GET("select_cartoonimage.php")
    Call<JsonItem2[]>getCartoonImageInfo();

    @Headers({
            "X-Naver-Client-Id: Qy2ragtopqNOuunPqHek",
            "X-Naver-Client-Secret: Y7KSUryrTK"
    })
    @GET("geocode?")
Call<ResponseBody>getCallMapDataAPI(@Query("query") String addr);
}
