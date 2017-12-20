package com.minimalsoft.smsmx.Utils;

import com.minimalsoft.smsmx.Models.requests.RegisterDeviceRequest;
import com.minimalsoft.smsmx.Models.responses.LoginResponse;
import com.minimalsoft.smsmx.Models.responses.MessageListResponse;
import com.minimalsoft.smsmx.Models.requests.StatusRequest;
import com.minimalsoft.smsmx.Models.responses.RegisterDeviceResponse;
import com.minimalsoft.smsmx.Models.responses.StatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by David Morales on 18/12/17.
 */

public interface WServices {

    @Headers({"Auth:Basic c21zTVg6c21zR2F0ZXdheTEyMyE=", "Content-Type:application/json"})
    @POST("v1/report")
    Call<StatusResponse> updateStatus(@Body StatusRequest request);

    @Headers({"Auth:Basic c21zTVg6c21zR2F0ZXdheTEyMyE=", "Content-Type:application/json"})
    @GET("v1/report")
    Call<MessageListResponse> updateSMSList(@Query("date") String date, @Query("limit") int limit, @Query("offset") int offset);

    @Headers({"Auth:Basic c21zTVg6c21zR2F0ZXdheTEyMyE=", "Content-Type:application/json"})
    @GET("v1/auth/login")
    Call<LoginResponse> login(@Body LoginResponse request);

    @Headers({"Auth:Basic c21zTVg6c21zR2F0ZXdheTEyMyE=", "Content-Type:application/json"})
    @GET("v1/config/registerDevice")
    Call<RegisterDeviceResponse> registerDevice(@Body RegisterDeviceRequest request);
}
