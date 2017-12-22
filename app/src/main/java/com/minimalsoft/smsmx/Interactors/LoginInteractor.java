package com.minimalsoft.smsmx.Interactors;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.minimalsoft.smsmx.Models.requests.LoginRequest;
import com.minimalsoft.smsmx.Models.responses.LoginResponse;
import com.minimalsoft.smsmx.Presenters.LoginPresenter;
import com.minimalsoft.smsmx.Presenters.LoginPresenterI;
import com.minimalsoft.smsmx.Utils.Constants;
import com.minimalsoft.smsmx.Utils.WServices;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David Morales on 11/1/17.
 */

public class LoginInteractor implements LoginInteractorI {

    private LoginPresenterI loginPresenter;

    public LoginInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void login(String user, String password) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WServices apiService = retrofit.create(WServices.class);

        Call<LoginResponse> call = apiService.login(new LoginRequest(user, password));
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> retrofitResponse) {

                LoginResponse response = retrofitResponse.body();

                if (response != null) {
                    if (response.getCode() != null && response.getCode().equals("200")) {
                        loginPresenter.onLoginSuccess(response);
                    }
                } else {
                    loginPresenter.onLoginFailed("Respuesta vacia del servidor.");
                }


            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginPresenter.onLoginFailed("Verifica tu conexion a internet y vuelve a intentarlo");
            }
        });

    }
}
