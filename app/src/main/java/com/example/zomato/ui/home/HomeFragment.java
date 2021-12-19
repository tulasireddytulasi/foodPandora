package com.example.zomato.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.adapters.BiryaniAdapter;
import com.example.zomato.modelclass.BiryaniDataModel;
import com.example.zomato.networkCalls.RetrofitClient;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View view;
    private HomeViewModel homeViewModel;
    private List<BiryaniDataModel> dataArrayList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BiryaniAdapter biryaniAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

      //  if(view == null){
            View view = inflater.inflate(R.layout.fragment_home, container, false);
            dataArrayList = new ArrayList<>();
            recyclerView = view.findViewById(R.id.recyclerview);
            shimmerFrameLayout = view.findViewById(R.id.shimmer);
            shimmerFrameLayout.startShimmer();
            linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            GetBiryaniList();
      //  }
//        else {
//            ((ViewGroup) view.getParent()).removeView(view);
//        }

        return view;
    }

    private void GetBiryaniList() {
        Log.d("Data555", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        RetrofitClient.getInstance()
                .getApi()
                .getBiryaniList("Dum Biryani")
                .enqueue(new Callback<List<BiryaniDataModel>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Response<List<BiryaniDataModel>> response) {
                        if (response.body() != null) {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            dataArrayList =  response.body();
                           // Toasty.success(getContext(), dataArrayList.get(0).getName(), Toast.LENGTH_SHORT, true).show();
                            Log.d("Data555",dataArrayList.get(0).getName());
                            biryaniAdapter = new BiryaniAdapter(getContext(), dataArrayList);
                            recyclerView.setAdapter(biryaniAdapter);
//                            activityMainBinding.coordinator.setVisibility(View.VISIBLE);
//                            nowplayingdata = response.body().getResults();
//                            adapter1 = new RecyclerviewAdapter1(MainActivity.this, nowplayingdata, MainActivity.this);
//                            activityMainBinding.recyclerview1.setAdapter(adapter1);
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<List<BiryaniDataModel>> call, @NotNull Throwable t) {
                        Log.d("Data555666", t.toString());
                    }
                });
    }
}