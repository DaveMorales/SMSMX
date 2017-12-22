package com.minimalsoft.smsmx.Views.Activities;

import com.minimalsoft.smsmx.Models.responses.LoginResponse;

/**
 * Created by David Morales on 11/1/17.
 */

public interface LoginActivityI {

    void onLoginSuccess(LoginResponse response);

    void onLoginFailed(String error);

}
