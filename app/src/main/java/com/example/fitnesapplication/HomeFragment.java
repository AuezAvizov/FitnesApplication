package com.example.fitnesapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnesapplication.Yoga.Joke;
import com.example.fitnesapplication.Yoga.JokeApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private TextView setupTextView;
    private TextView punchlineTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupTextView = view.findViewById(R.id.setupTextView);
        punchlineTextView = view.findViewById(R.id.punchlineTextView);

        fetchJoke();

        return view;
    }

    private void fetchJoke() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JokeApi jokeApi = retrofit.create(JokeApi.class);
        Call<Joke> call = jokeApi.getRandomJoke();
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Joke joke = response.body();
                    setupTextView.setText(joke.getSetup());
                    punchlineTextView.setText(joke.getPunchline());
                } else {
                    setupTextView.setText("Failed to load joke");
                    punchlineTextView.setText("");
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                setupTextView.setText("Error: " + t.getMessage());
                punchlineTextView.setText("");
                Log.e(TAG, "API call failed", t);
            }
        });
    }
}