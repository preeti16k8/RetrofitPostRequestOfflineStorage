package com.preeti.interviewsuryasofttask.Network;
import com.preeti.interviewsuryasofttask.Model.EmailAddress;
import com.preeti.interviewsuryasofttask.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("list")
    Call<LoginResponse> createUser(@Body LoginResponse login);

    @POST("list")
    Call<Object> getResponse(@Field("body") String body);

    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST("list")
    Call<LoginResponse> getFriendsLocation(@Body EmailAddress friendModel);


}
