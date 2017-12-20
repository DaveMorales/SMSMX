package com.minimalsoft.smsmx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minimalsoft.smsmx.Models.responses.MessageListResponse.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by David Morales on 18/12/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Data> messages;

    public MainAdapter(List<Data> messages) {
        this.messages = messages;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txtNumber.setText(messages.get(i).getNumber());
        viewHolder.txtStatus.setText(messages.get(i).getStatus());
        viewHolder.txtMessage.setText(messages.get(i).getMessage());

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).parse(messages.get(i).getTimestamp()));

            boolean sameDay = Calendar.getInstance().get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);

            if (sameDay)
                viewHolder.txtTime.setText(new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).parse(messages.get(i).getTimestamp())));
            else
                viewHolder.txtTime.setText(messages.get(i).getTimestamp());

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNumber, txtStatus, txtTime, txtMessage;

        public ViewHolder(View view) {
            super(view);

            txtNumber = (TextView) view.findViewById(R.id.txt_number);
            txtStatus = (TextView) view.findViewById(R.id.txt_status);
            txtTime = (TextView) view.findViewById(R.id.txt_time);
            txtMessage = (TextView) view.findViewById(R.id.txt_message);

        }
    }
}
