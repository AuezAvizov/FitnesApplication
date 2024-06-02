package com.example.fitnesapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.fitnesapplication.Yoga.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WorkoutFragment extends Fragment {

    private static final String TAG = "WorkoutFragment";
    private TextView titleTextView;
    private TextView exerciseTextView;
    private TextView descriptionTextView;
    private TextView timerTextView;
    private Button startButton;
    private Button completeButton;
    private ImageView imageView;
    private int exerciseIndex = 0;
    private Exercise currentExercise;
    private CountDownTimer timer;
    private List<Exercise> exercises;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        titleTextView = view.findViewById(R.id.titleTextView);
        exerciseTextView = view.findViewById(R.id.exerciseTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        timerTextView = view.findViewById(R.id.timerTextView);
        startButton = view.findViewById(R.id.startButton);
        completeButton = view.findViewById(R.id.completeButton);
        imageView = view.findViewById(R.id.imageView);

        exercises = loadExercisesFromJson();

        if (exercises == null || exercises.isEmpty()) {
            Log.e(TAG, "Failed to load exercises or exercises list is empty.");
            titleTextView.setText("Failed to load exercises.");
            startButton.setEnabled(false);
        } else {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startWorkout();
                }
            });

            completeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    completeExercise();
                }
            });
        }

        return view;
    }

    private void startWorkout() {
        exerciseIndex = 0;
        titleTextView.setText("Workout Started");
        startButton.setEnabled(false);
        startButton.setText("Workout In Progress");
        startNextExercise();
    }

    private void completeExercise() {
        if (timer != null) {
            timer.cancel();
        }
        completeButton.setEnabled(false);
        startNextExercise();
    }

    private void startNextExercise() {
        if (exercises != null && exerciseIndex < exercises.size()) {
            currentExercise = exercises.get(exerciseIndex);
            exerciseTextView.setText(currentExercise.getName());
            descriptionTextView.setText(currentExercise.getDescription());
            Glide.with(this)
                    .asGif()
                    .load(currentExercise.getGifImageUrl())
                    .into(imageView);
            timerTextView.setText(formatTime(currentExercise.getDurationInSeconds()));

            timer = new CountDownTimer(currentExercise.getDurationInSeconds() * 1000L, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(formatTime((int) (millisUntilFinished / 1000)));
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("Exercise Complete");
                    imageView.setVisibility(View.VISIBLE);
                    completeButton.setEnabled(true);
                }
            }.start();

            exerciseIndex++;
        } else {
            exerciseTextView.setText("Workout Complete");
            descriptionTextView.setText("");
            timerTextView.setText("");
            completeButton.setEnabled(false);
            startButton.setEnabled(true);
            startButton.setText("Start Again");
        }
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private List<Exercise> loadExercisesFromJson() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("exercises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            Log.d(TAG, "JSON loaded successfully: " + json);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error loading JSON", ex);
            return null;
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Exercise>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}
