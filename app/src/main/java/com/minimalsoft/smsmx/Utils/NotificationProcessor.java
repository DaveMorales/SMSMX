package com.minimalsoft.smsmx.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.minimalsoft.smsmx.Interactors.MainActivityInteractor;
import com.minimalsoft.smsmx.Interactors.MainActivityInteractorI;
import com.minimalsoft.smsmx.Models.responses.SendMessageResponse;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by David Morales on 11/3/17. for SMS gateway
 */

public class NotificationProcessor extends NotificationExtenderService {

    private final String TAG = getClass().getSimpleName();


    MainActivityInteractorI mainActivityInteractor;

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {

        mainActivityInteractor = new MainActivityInteractor();

        Gson gson = new Gson();
        SendMessageResponse response = gson.fromJson(notification.payload.additionalData.toString(), SendMessageResponse.class);

        Log.e(TAG, gson.toJson(response));

        if (response != null) {
            switch (response.getType()) {
                case "configs":

                case "diffusion":
                case "alert":

                    List<SendMessageResponse.Message> messageList = response.getMessages();
                    if (messageList.size() >= 1) {
                        try {
                            for (int i = 0; i < messageList.size(); i++) {
                                mainActivityInteractor.sendSMS(messageList.get(i));
                                sleep(10000);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    return true;

                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
