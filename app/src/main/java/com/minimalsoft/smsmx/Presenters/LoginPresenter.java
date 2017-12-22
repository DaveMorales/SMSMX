package com.minimalsoft.smsmx.Presenters;

import android.util.Base64;

import com.minimalsoft.smsmx.Interactors.LoginInteractor;
import com.minimalsoft.smsmx.Interactors.LoginInteractorI;
import com.minimalsoft.smsmx.Models.responses.LoginResponse;
import com.minimalsoft.smsmx.Views.Activities.LoginActivity;
import com.minimalsoft.smsmx.Views.Activities.LoginActivityI;
import com.onesignal.OneSignal;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by David Morales on 11/1/17.
 */

public class LoginPresenter implements LoginPresenterI {

    private LoginActivityI loginActivity;
    private LoginInteractorI loginInteractor;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void login(String user, String password) {

        user = user.trim();
        password = password.trim();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aDigest : digest) {
                sb.append(Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1));
            }

            byte[] data = sb.toString().getBytes("UTF-8");
            password = Base64.encodeToString(data, Base64.DEFAULT);

            loginInteractor.login(user.trim(), password.trim());

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            loginActivity.onLoginFailed("Falló al encriptar el password");
        }
    }

    @Override
    public void onLoginSuccess(LoginResponse response) {

        if (response.getData().get(0).getIsAdmin() == 1)
            OneSignal.sendTag("isAdmin", "1");
        else
            OneSignal.sendTag("isAdmin", "0");

        if (response.getData().get(0).getIsSender() == 1)
            OneSignal.sendTag("isSender", "1");
        else
            OneSignal.sendTag("isSender", "0");

        loginActivity.onLoginSuccess(response);
    }

    @Override
    public void onLoginFailed(String error) {

        loginActivity.onLoginFailed(error);

    }
}
