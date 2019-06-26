package com.example.myapplication;

import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {
    @POST("user/login")
    Call<ResponseBody> getLogin(@Query("username") String username,
                                @Query("password") String password);
}
