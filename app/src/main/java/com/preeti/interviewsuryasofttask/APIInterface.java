package com.preeti.interviewsuryasofttask;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("list")
    Call<LoginResponse> createUser(@Body LoginResponse login);
}
