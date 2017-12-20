package com.minimalsoft.smsmx;

import android.content.Context;
import android.util.Log;

import com.onesignal.OneSignal;

/**
 * Created by David Morales on 11/1/17.
 */

public class Application extends android.app.Application {

    private static Context context;


    public static Context getContext(){
        return Application.context;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Application.context = getApplicationContext();


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.None)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();



        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {

                if (userId != null){
//                    session=new PlayerIdsession(context);
//                    session.savePlayerId(userId);
                    Log.e("Notification", "PlayerId:" + userId);
                }

            if (registrationId != null){
                Log.e("Notification", "registrationId:" + registrationId);
        }

            }
        });
    }


    /*class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String number,message,type;

            if (data != null) {

                number = data.optString("number", null);
                message = data.optString("message", null);
                type = data.optString("type", null);

                if (number != null && message != null && type != null)
                    Log.e("Notification", "Number: " + number + "\nMessage: " + message + "\nType: " +type);
            }

        }
    }*/
}
