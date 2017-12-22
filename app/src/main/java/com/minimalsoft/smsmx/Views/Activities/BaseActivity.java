package com.minimalsoft.smsmx.Views.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.minimalsoft.smsmx.R;
import com.minimalsoft.smsmx.Utils.Constants;

/**
 * Created by David Morales on 20/12/17.
 */

public class BaseActivity extends AppCompatActivity implements BaseActivityI {

    private ProgressDialog progressDialog;
    private Dialog simpleDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void showLoading(String message) {

        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();

    }

    @Override
    public void hideLoading() {
        progressDialog.cancel();
    }

    @Override
    public void showSimpleDialog(String message) {
        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }

        builder.setTitle(R.string.app_name);
        builder.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setMessage(message);

        try {
            if (simpleDialog != null && simpleDialog.isShowing())
                simpleDialog.dismiss();
            simpleDialog = builder.create();
            simpleDialog.show();
        } catch (Exception ex) {
            simpleDialog = null;
        }
    }

    /**
     * Method for saving a string key.
     *
     * @param context Context of the application.
     * @param key     Key for the value to know.
     * @param value   Value of the key.
     */
    public static void setStringKey(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    /**
     * Method that gets a value for a String shared preference key.
     *
     * @param context Context of the application.
     * @param key     Key for the value to know.
     * @return Value of the key.
     */
    public static String getStringKey(Context context, String key) {
        return context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getString(key, Constants.SMS_NULL);
    }

    /**
     * Method for saving a boolean key.
     *
     * @param context Context of the application.
     * @param key     Key for the value to know.
     * @param value   Value of the key.
     */
    public static void setBooleanPreference(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Method that gets a value for a boolean shared preference key.
     *
     * @param context Context of the application.
     * @param key     Key for the value to know.
     * @return Value of the key.
     */
    public static boolean getBooleanPreference(Context context, String key) {
        try {
            return context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getBoolean(key, false);
        } catch (ClassCastException ex) {
            try {
                return Boolean.parseBoolean(context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getString(key, "false"));
            } catch (Exception ex2) {
                return false;
            }
        }
    }
}
