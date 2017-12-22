package com.minimalsoft.smsmx.Presenters;

import com.minimalsoft.smsmx.Models.responses.LoginResponse;

/**
 * Created by David Morales on 11/1/17.
 */

public interface LoginPresenterI {

    void login(String user, String password);

    void onLoginSuccess(LoginResponse response);

    void onLoginFailed(String error);

}
