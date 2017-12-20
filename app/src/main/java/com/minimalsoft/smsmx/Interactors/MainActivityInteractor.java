package com.minimalsoft.smsmx.Interactors;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.util.Log;

import com.minimalsoft.smsmx.Application;
import com.minimalsoft.smsmx.Models.responses.MessageListResponse;
import com.minimalsoft.smsmx.Utils.SMStatus;
import com.minimalsoft.smsmx.Models.requests.StatusRequest;
import com.minimalsoft.smsmx.Models.responses.StatusResponse;
import com.minimalsoft.smsmx.Presenters.MainActivityPresenter;
import com.minimalsoft.smsmx.Presenters.MainActivityPresenterI;
import com.minimalsoft.smsmx.Utils.WServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.app.PendingIntent.FLAG_ONE_SHOT;
import static com.minimalsoft.smsmx.Utils.StaticVars.BASE_URL;

/**
 * Created by David Morales on 11/1/17.
 */

public class MainActivityInteractor implements MainActivityInteractorI {

    private final String TAG = "SMS_MX";

    private String SENT = "SMS_SENT";
    private String DELIVERED = "SMS_DELIVERED";

    private MainActivityPresenterI mainActivityPresenter;

    public MainActivityInteractor(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    public MainActivityInteractor() {
    }

    private void setListeners() {

        // ---when the SMS has been sent---
        Application.getContext().registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context arg0, Intent intent) {

                        int idSMS = 0;
                        SMStatus status;

                        if (intent.getExtras() != null) {
                            idSMS = intent.getIntExtra("id", 0);
                        }

                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                status = SMStatus.SENDED;
                                Log.d(TAG + " " + idSMS, "Send Status: SENDED");
                                break;

                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                status = SMStatus.FAILED_GENERIC;
                                Log.d(TAG + " " + idSMS, "Send Status: RESULT_ERROR_GENERIC_FAILURE");
                                break;

                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                status = SMStatus.FAILED_NO_SERVICE;
                                Log.d(TAG + " " + idSMS, "Send Status: RESULT_ERROR_NO_SERVICE");
                                break;

                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                status = SMStatus.FAILED_NULL_PDU;
                                Log.d(TAG + " " + idSMS, "Send Status: RESULT_ERROR_NULL_PDU");
                                break;

                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                status = SMStatus.FAILED_RADIO_OFF;
                                Log.d(TAG + " " + idSMS, "Send Status: RESULT_ERROR_RADIO_OFF");
                                break;

                            default:
                                Log.d(TAG + " " + idSMS, "Delivered Status: UNKNOWN");
                                status = SMStatus.UNKNOWN;
                                getResultCode();
                                break;
                        }

                        if (idSMS != 0)
                            sendStatus2Server(idSMS + "", status);
                        Application.getContext().unregisterReceiver(this);

                    }
                }, new IntentFilter(SENT));


        // ---when the SMS has been delivered---
        Application.getContext().registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context arg0, Intent intent) {

                        String number = "";
                        int idSMS = 0;
                        SMStatus status;

                        if (intent.getExtras() != null) {
                            idSMS = intent.getIntExtra("id", 0);
                        }

                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                status = SMStatus.DELIVERED;
                                Log.d(TAG + " " + idSMS, "Delivered Status: DELIVERED");
                                break;
                            case Activity.RESULT_CANCELED:
                                status = SMStatus.NOT_DELIVERED;
                                Log.d(TAG + " " + idSMS, "Delivered Status: FAILED");
                                break;
                            default:
                                Log.d(TAG + " " + idSMS, "Delivered Status: UNKNOWN");
                                status = SMStatus.UNKNOWN;
                                getResultCode();
                                break;
                        }

                        if (idSMS != 0)
                            sendStatus2Server(idSMS + "", status);
                        Application.getContext().unregisterReceiver(this);

                    }
                }, new IntentFilter(DELIVERED));
    }

    private void sendStatus2Server(final String idSMS, SMStatus status) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WServices apiService = retrofit.create(WServices.class);

        StatusRequest request = new StatusRequest(idSMS, status.toString());
        Call<StatusResponse> call = apiService.updateStatus(request);
        call.enqueue(new Callback<StatusResponse>() {

            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {

                if (response.isSuccessful())
                    Log.d(TAG + " " + idSMS, "Status updated: " + response.body().getMessage());
            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                Log.e(TAG + " " + idSMS, "Status update failed!: ", t);
            }
        });

    }

    @Override
    public void sendSMS(Message message) {

        Log.e(TAG + " " + message.getId() + ": ", "Message to: " + message.getNumber() + " id: " + message.getId());

        Intent intentSent = new Intent(SENT);
        intentSent.putExtra("id", message.getId());

        Intent intentDelivered = new Intent(DELIVERED);
        intentDelivered.putExtra("id", message.getId());

        PendingIntent sentPI = PendingIntent.getBroadcast(Application.getContext(), 0, intentSent, FLAG_CANCEL_CURRENT | FLAG_ONE_SHOT);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(Application.getContext(), 0, intentDelivered, FLAG_CANCEL_CURRENT | FLAG_ONE_SHOT);

        setListeners();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(message.getNumber(), null, message.getMessage(), sentPI, deliveredPI);
        } catch (Exception ex) {
            Log.e(TAG, "Message to : " + message.getNumber() + "\nfailed with: ".concat(ex.toString()));
            ex.printStackTrace();
        }

    }

    @Override
    public void updateSMSList(String date, int limit, int offset) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WServices apiService = retrofit.create(WServices.class);

        Call<MessageListResponse> call = apiService.updateSMSList(date, limit, offset);
        call.enqueue(new Callback<MessageListResponse>() {
            @Override
            public void onResponse(Call<MessageListResponse> call, Response<MessageListResponse> response) {
                if (response.isSuccessful())
                    mainActivityPresenter.onUpdateSMSListSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MessageListResponse> call, Throwable t) {
                mainActivityPresenter.onUpdateSMSListFailure();
            }
        });
    }

}
