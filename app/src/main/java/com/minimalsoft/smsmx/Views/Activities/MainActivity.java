package com.minimalsoft.smsmx.Views.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.minimalsoft.smsmx.MainAdapter;
import com.minimalsoft.smsmx.Models.responses.MessageListResponse;
import com.minimalsoft.smsmx.Presenters.MainPresenter;
import com.minimalsoft.smsmx.Presenters.MainPresenterI;
import com.minimalsoft.smsmx.R;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends BaseActivity implements MainActivityI {

    MainPresenterI mainActivityPresenter;
    RecyclerView mainRv;
    SwipeRefreshLayout swipeRefreshLayout;
//    DatePickerTimeline timeline;
    HorizontalPicker calendar;

    TextView txtSent, txtAlert, txtDiffusion, txtFailed;

    int DAY, MONTH, YEAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainPresenter(this);

        txtSent = findViewById(R.id.txt_sent);
        txtAlert = findViewById(R.id.txt_alert);
        txtDiffusion = findViewById(R.id.txt_difusion);
        txtFailed = findViewById(R.id.txt_error);

        mainRv = (RecyclerView) findViewById(R.id.main_rv);
        mainRv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mainRv.setLayoutManager(layoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainActivityPresenter.updateSMSList(getDate(), 100, 0);
            }
        });

        calendar = (HorizontalPicker) findViewById(R.id.calendar);
        calendar.setListener(new DatePickerListener() {
            @Override
            public void onDateSelected(DateTime dateSelected) {

                DAY = dateSelected.getDayOfMonth();
                MONTH = dateSelected.getMonthOfYear();
                YEAR = dateSelected.getYear();

                mainActivityPresenter.updateSMSList(getDate(), 1000, 0);
            }
        })
                .init();

        calendar.setDate(new DateTime());

//        timeline = findViewById(R.id.calendar);
//        timeline.centerOnSelection();
//        timeline.setFirstVisibleDate(2017, Calendar.DECEMBER, 1);
//        timeline.setLastVisibleDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        timeline.setSelectedDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(int year, int month, int day, int index) {
//                DAY = day;
//                MONTH = month;
//                YEAR = year;
//
//                mainActivityPresenter.updateSMSList(getDate(), 1000, 0);
//            }
//        });
//
//        timeline.setFollowScroll(true);
//        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
//            @Override
//            public CharSequence getLabel(Calendar calendar, int index) {
//                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityPresenter.updateSMSList(getDate(), 100, 0);

    }

    @Override
    public void onUpdateSMSListSuccess(MessageListResponse response) {

        txtSent.setText(response.getTotalDaily());
        txtAlert.setText(response.getAlertSent());
        txtDiffusion.setText(response.getDiffusionSent());
        txtFailed.setText(response.getFailed());

        swipeRefreshLayout.setRefreshing(false);
        MainAdapter adapter = new MainAdapter(response.getData());
        mainRv.setAdapter(adapter);
    }

    @Override
    public void onUpdateSMSListFailed(String error) {

    }


    public String getDate() {

        if (DAY != 0 & MONTH != 0 & YEAR != 0) {

            return String.format(Locale.getDefault(), "%04d-%02d-%02d", YEAR, MONTH, DAY);

        } else {
            return String.format(Locale.getDefault(), "%04d-%02d-%02d",
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH)+1,
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }

    }
}
