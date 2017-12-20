package com.minimalsoft.smsmx.Interactors;

import com.minimalsoft.smsmx.Models.responses.SendMessageResponse;

/**
 * Created by David Morales on 11/1/17.
 */

public interface MainActivityInteractorI {

    void sendSMS(SendMessageResponse.Message message);

    void updateSMSList(String date, int limit, int offset);

}
