package com.example.fitnesapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesapplication.Yoga.Category;

import java.util.List;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.WeekDayViewHolder> {

    private final List<String> daysOfWeek;
    private final List<Category> exercises;
    private final Context context;

    public WeekDayAdapter(List<String> daysOfWeek, List<Category> exercises, Context context) {
        this.daysOfWeek = daysOfWeek;
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public WeekDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new WeekDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekDayViewHolder holder, int position) {
        String day = daysOfWeek.get(position);
        holder.dayTextView.setText(day);
        if (position < exercises.size()) {
            Category exercise = exercises.get(position);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ExerciseDetailActivity.class);
                intent.putExtra("exercise", exercise);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return daysOfWeek.size();
    }

    static class WeekDayViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;

        WeekDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }
    }
}