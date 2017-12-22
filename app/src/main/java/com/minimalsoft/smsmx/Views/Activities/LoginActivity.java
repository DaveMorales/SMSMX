package com.minimalsoft.smsmx.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.minimalsoft.smsmx.Models.responses.LoginResponse;
import com.minimalsoft.smsmx.Presenters.LoginPresenter;
import com.minimalsoft.smsmx.Presenters.LoginPresenterI;
import com.minimalsoft.smsmx.R;
import com.minimalsoft.smsmx.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginActivityI {

    @BindView(R.id.txt_user)
    TextInputEditText txtUser;

    @BindView(R.id.txt_password)
    TextInputEditText txtPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    LoginPresenterI loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getBooleanPreference(getApplicationContext(), Constants.IS_LOGGED)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading("Iniciando Sesion...");

                if (txtUser.getText().toString().isEmpty()) {
                    txtUser.setError("El campo es obligatorio");
                    return;
                }
                if (txtPassword.getText().toString().isEmpty()) {
                    txtPassword.setError("El campo es obligatorio");
                    return;
                }

                loginPresenter.login(txtUser.getText().toString(), txtPassword.getText().toString());
            }
        });

    }

    @Override
    public void onLoginSuccess(LoginResponse response) {
        hideLoading();

        setBooleanPreference(getApplicationContext(), Constants.IS_LOGGED, true);

        setStringKey(getApplicationContext(), Constants.ID_USER, response.getData().get(0).getIdUser() + "");
        setStringKey(getApplicationContext(), Constants.USER_NAME, response.getData().get(0).getName());

        if (response.getData().get(0).getIsAdmin() == 1)
            setBooleanPreference(getApplicationContext(), Constants.IS_ADMIN, true);
        else
            setBooleanPreference(getApplicationContext(), Constants.IS_ADMIN, false);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(String error) {
        hideLoading();
        showSimpleDialog(error);
    }
}
