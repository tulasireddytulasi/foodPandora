package com.example.zomato.networkCalls;

import com.example.zomato.modelclass.BiryaniDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("getimages.php")
    Call<List<BiryaniDataModel>> getBiryaniList(
            @Query("category") String category
    );

}


