package com.minimalsoft.smsmx.Presenters;

import com.minimalsoft.smsmx.Interactors.MainInteractor;
import com.minimalsoft.smsmx.Interactors.MainInteractorI;
import com.minimalsoft.smsmx.Models.responses.MessageListResponse;
import com.minimalsoft.smsmx.Views.Activities.MainActivityI;

/**
 * Created by David Morales on 11/1/17.
 */

public class MainPresenter implements MainPresenterI {

    MainActivityI mainActivity;
    MainInteractorI mainActivityInteractor;

    public MainPresenter(MainActivityI mainActivity) {
        this.mainActivity = mainActivity;
        mainActivityInteractor = new MainInteractor(this);
    }

    @Override
    public void updateSMSList(String date, int limit, int offset) {

        mainActivityInteractor.updateSMSList(date, limit, offset);

    }

    @Override
    public void onUpdateSMSListSuccess(MessageListResponse response) {

        //mainActivity.hideLoading();

        if (response.getCode().equals("200")) {
            mainActivity.onUpdateSMSListSuccess(response);
        }
    }

    @Override
    public void onUpdateSMSListFailure() {

    }
}
