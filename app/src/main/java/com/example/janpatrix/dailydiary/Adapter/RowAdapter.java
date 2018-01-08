package com.example.janpatrix.dailydiary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janpatrix.dailydiary.Events.EventObject;
import com.example.janpatrix.dailydiary.R;
import com.example.janpatrix.dailydiary.RoomEvents.Card;

import java.util.List;


/**
 * Created by patrickgross on 04.01.18.
 */

public class RowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Card> mList;

    public RowAdapter(List<Card> list){
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {

            case Card.ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new ItemViewHolder(view);

            case Card.EVENT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
                return new EventViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Card object = mList.get(position);
        if (object != null) {
            switch(object.getType()) {
                case Card.ITEM_TYPE:
                    ((ItemViewHolder) holder).mTitle.setText(object.getMessage());
                    break;

                case Card.EVENT_TYPE:
                    ((EventViewHolder) holder).mTitle.setText(object.getMessage());
                    ((EventViewHolder) holder).mDescription.setText("" + object.getDate());
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
            Card object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }

    public class EventViewHolder extends  RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDescription;

        public EventViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_event_title);
            mDescription = itemView.findViewById(R.id.tv_event_description);
        }
    }
}
