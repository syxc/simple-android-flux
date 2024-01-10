package com.github.android.flux;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.android.flux.actions.ActionsCreator;
import com.github.android.flux.model.Singer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlove on 2018/2/5.
 */
public class SingerRecyclerAdapter extends RecyclerView.Adapter<SingerRecyclerAdapter.ViewHolder> {

    private static ActionsCreator actionsCreator;
    private List<Singer> singers;

    public SingerRecyclerAdapter(ActionsCreator actionsCreator) {
        this.singers = new ArrayList<>();
        SingerRecyclerAdapter.actionsCreator = actionsCreator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_singer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(singers.get(position));
    }

    @Override
    public int getItemCount() {
        return singers.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Singer> singers) {
        this.singers = singers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAge;
        public TextView tvName;
        public TextView tvGender;
        public Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvGender = itemView.findViewById(R.id.gender);
            tvAge = itemView.findViewById(R.id.age);
            btnDelete = itemView.findViewById(R.id.delete);
        }

        @SuppressLint("SetTextI18n")
        public void bindView(final Singer singer) {
            tvName.setText("name: " + singer.getName());
            tvGender.setText("gender: " + singer.getGender());
            tvAge.setText("age: " + singer.getAge());

            btnDelete.setOnClickListener(v -> {
                long id = singer.getId();
                actionsCreator.delete(id);
            });
        }
    }
}
