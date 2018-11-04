package com.example.praba.prakmob.api;

import com.example.praba.prakmob.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("user/login")
    Call<UserLogin>login(@Field("username") String username, @Field("password") String password);

//    @GET("contact")
//    Call<List<Contact>> getContact();
}
