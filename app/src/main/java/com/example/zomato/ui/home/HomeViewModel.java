package com.example.zomato.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomato.modelclass.BiryaniDataModel;
import com.example.zomato.networkCalls.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<BiryaniDataModel>> biryaniFoodLiveData;
    private List<BiryaniDataModel> biryaniFoodLiveDataArrayList;

    private MutableLiveData<List<BiryaniDataModel>> burgersFoodLiveData;
    private List<BiryaniDataModel> burgersFoodLiveDataArrayList;

    public LiveData<List<BiryaniDataModel>> getBiryaniList() {
        if (biryaniFoodLiveData == null) {
            biryaniFoodLiveData = new MutableLiveData<>();
            GetBiryaniListData();
        }
        return biryaniFoodLiveData;
    }

    public LiveData<List<BiryaniDataModel>> getBurgerList() {
        if (burgersFoodLiveData == null) {
            burgersFoodLiveData = new MutableLiveData<>();
            GetBurgerListData();
        }
        return burgersFoodLiveData;
    }

    private void GetBiryaniListData() {
        RetrofitClient.getInstance()
                .getApi()
                .getBiryaniList("Dum Biryani")
                .enqueue(new Callback<List<BiryaniDataModel>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Response<List<BiryaniDataModel>> response) {
                        if (response.body() != null) {
                            biryaniFoodLiveDataArrayList = response.body();
                            biryaniFoodLiveData.setValue(biryaniFoodLiveDataArrayList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Throwable t) {
                        Log.d("GetBiryaniList Error", t.toString());
                    }
                });
    }

    private void GetBurgerListData() {
        RetrofitClient.getInstance()
                .getApi()
                .getBurgerList("burgers")
                .enqueue(new Callback<List<BiryaniDataModel>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Response<List<BiryaniDataModel>> response) {
                        if (response.body() != null) {
                            burgersFoodLiveDataArrayList = response.body();
                            burgersFoodLiveData.setValue(burgersFoodLiveDataArrayList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Throwable t) {
                        Log.d("GetBurgerListData Error", t.toString());
                    }
                });
    }
}