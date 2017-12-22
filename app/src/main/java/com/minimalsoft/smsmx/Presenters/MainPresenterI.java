package com.minimalsoft.smsmx.Presenters;

import com.minimalsoft.smsmx.Models.responses.MessageListResponse;

/**
 * Created by David Morales on 11/1/17.
 */

public interface MainPresenterI {

    void updateSMSList(String date, int limit, int offset);

    void onUpdateSMSListSuccess(MessageListResponse response);

    void onUpdateSMSListFailure();

//    void updateSMSList();

//    void updateSMSList();

}
