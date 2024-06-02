package com.example.fitnesapplication.Yoga;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YogaApi {
    @GET("v1/categories")
    Call<List<Category>> getCategories();
}
