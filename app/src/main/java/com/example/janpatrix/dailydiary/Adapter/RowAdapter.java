package com.example.janpatrix.dailydiary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janpatrix.dailydiary.Activities.CalendarActivity;
import com.example.janpatrix.dailydiary.Events.EventObject;
import com.example.janpatrix.dailydiary.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by patrickgross on 04.01.18.
 */

public class RowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EventObject> mList;

    public RowAdapter(List<EventObject> list){
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {

            case EventObject.ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new ItemViewHolder(view);

            case EventObject.EVENT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
                return new EventViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        EventObject object = mList.get(position);
        if (object != null) {
            switch(object.getType()) {
                case EventObject.ITEM_TYPE:
                    ((ItemViewHolder) holder).mTitle.setText(object.getMessage());
                    break;
                case EventObject.EVENT_TYPE:
                    ((EventViewHolder) holder).mTitle.setText(object.getMessage());
                    ((EventViewHolder) holder).mDescription.setText(convertDateToString(object.getDate()));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            EventObject object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }

    public static class EventViewHolder extends  RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDescription;

        public EventViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_event_title);
            mDescription = itemView.findViewById(R.id.tv_event_description);
        }
    }

    private String convertDateToString(Date mDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        return dateFormat.format(mDate);
    }
}
