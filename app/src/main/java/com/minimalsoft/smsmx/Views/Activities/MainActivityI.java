package com.minimalsoft.smsmx.Views.Activities;

import com.minimalsoft.smsmx.Models.responses.MessageListResponse;

/**
 * Created by David Morales on 11/1/17.
 */

public interface MainActivityI {

    void onUpdateSMSListSuccess(MessageListResponse response);

    void onUpdateSMSListFailed(String error);

}
