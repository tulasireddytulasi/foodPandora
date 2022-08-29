package com.example.zomato.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.adapters.BiryaniAdapter;
import com.example.zomato.adapters.BurgerAdapter;
import com.example.zomato.modelclass.BiryaniDataModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView burgerRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager burgerLinearLayoutManager;
    private ShimmerFrameLayout shimmerFrameLayout, burgerShimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.biryani_recyclerview);
        burgerRecyclerView = view.findViewById(R.id.burger_recyclerview);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        burgerShimmerFrameLayout = view.findViewById(R.id.shimmer2);
        shimmerFrameLayout.startShimmer();
        burgerShimmerFrameLayout.startShimmer();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        burgerLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        burgerRecyclerView.setHasFixedSize(true);
        homeViewModel.getBiryaniList().observe(getViewLifecycleOwner(), biryaniListUpdateObserver);
        homeViewModel.getBurgerList().observe(getViewLifecycleOwner(), burgersListUpdateObserver);
        return view;
    }

    private final Observer<List<BiryaniDataModel>> biryaniListUpdateObserver = new Observer<List<BiryaniDataModel>>() {
        @Override
        public void onChanged(List<BiryaniDataModel> biryaniDataArrayList) {
            recyclerView.setLayoutManager(linearLayoutManager);
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            BiryaniAdapter biryaniAdapter = new BiryaniAdapter(requireActivity(), biryaniDataArrayList);
            recyclerView.setAdapter(biryaniAdapter);
        }
    };

    private final Observer<List<BiryaniDataModel>> burgersListUpdateObserver = new Observer<List<BiryaniDataModel>>() {
        @Override
        public void onChanged(List<BiryaniDataModel> burgersDataArrayList) {
            burgerRecyclerView.setLayoutManager(burgerLinearLayoutManager);
            burgerShimmerFrameLayout.stopShimmer();
            burgerShimmerFrameLayout.setVisibility(View.GONE);
            burgerRecyclerView.setVisibility(View.VISIBLE);
            BurgerAdapter burgerAdapter = new BurgerAdapter(requireActivity(), burgersDataArrayList);
            burgerRecyclerView.setAdapter(burgerAdapter);
        }
    };
}