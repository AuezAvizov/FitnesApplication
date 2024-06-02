package com.example.fitnesapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesapplication.Yoga.Category;
import com.example.fitnesapplication.Yoga.YogaApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExerciseFragment extends Fragment {

    private RecyclerView rvWeekDays;
    private WeekDayAdapter weekDayAdapter;
    private List<String> daysOfWeek;
    private List<Category> exercises;
    private static final String TAG = "ExerciseFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        rvWeekDays = view.findViewById(R.id.rvWeekDays);
        rvWeekDays.setLayoutManager(new LinearLayoutManager(getContext()));

        daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        exercises = new ArrayList<>();
        weekDayAdapter = new WeekDayAdapter(daysOfWeek, exercises, getContext());
        rvWeekDays.setAdapter(weekDayAdapter);

        fetchExercises();

        return view;
    }

    private void fetchExercises() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://yoga-api-nzy4.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YogaApi yogaApi = retrofit.create(YogaApi.class);
        Call<List<Category>> call = yogaApi.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> fetchedExercises = response.body();
                    for (int i = 0; i < daysOfWeek.size(); i++) {
                        exercises.add(fetchedExercises.get(i % fetchedExercises.size()));
                    }
                    weekDayAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Data loaded successfully: " + exercises.size());
                } else {
                    try {
                        ResponseBody errorBody = response.errorBody();
                        if (errorBody != null) {
                            Log.d(TAG, "Response not successful: " + errorBody.string());
                        } else {
                            Log.d(TAG, "Response not successful: " + response.message());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
            }
        });
    }
}
