package com.minimalsoft.smsmx.Presenters;

import com.minimalsoft.smsmx.Interactors.MainActivityInteractor;
import com.minimalsoft.smsmx.Interactors.MainActivityInteractorI;
import com.minimalsoft.smsmx.Models.responses.MessageListResponse;
import com.minimalsoft.smsmx.Views.Activities.MainActivityI;

/**
 * Created by David Morales on 11/1/17.
 */

public class MainActivityPresenter implements MainActivityPresenterI {

    MainActivityI mainActivity;
    MainActivityInteractorI mainActivityInteractor;

    public MainActivityPresenter(MainActivityI mainActivity) {
        this.mainActivity = mainActivity;
        mainActivityInteractor = new MainActivityInteractor(this);
    }

    @Override
    public void updateSMSList(String date, int limit, int offset) {

        mainActivityInteractor.updateSMSList(date, limit, offset);

    }

    @Override
    public void onUpdateSMSListSuccess(MessageListResponse response) {

        mainActivity.hideLoading();

        if (response.getCode().equals("200")) {
            mainActivity.onUpdateSMSListSuccess(response);
        }
    }

    @Override
    public void onUpdateSMSListFailure() {

    }
}
