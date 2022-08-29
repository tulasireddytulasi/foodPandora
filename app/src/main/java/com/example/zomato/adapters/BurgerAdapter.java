package com.example.zomato.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zomato.R;
import com.example.zomato.modelclass.BiryaniDataModel;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerAdapterViewHolder> {

    private final Context context;
    private final List<BiryaniDataModel> biryaniDataModelList;
    public BurgerAdapter(Context context, List<BiryaniDataModel> biryaniDataModelList) {
        this.context = context;
        this.biryaniDataModelList = biryaniDataModelList;
    }

    @NonNull
    @Override
    public BurgerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biryanilist_layout, parent, false);
        return new BurgerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerAdapterViewHolder holder, int position) {

        Glide.with(context)
                .load("https://marvelwallpapers.000webhostapp.com/"+ biryaniDataModelList.get(position).getName())
                .placeholder(R.drawable.poster1)
                .centerCrop()
                .into(holder.imageView);
        holder.name.setText(R.string.chicken_burger);
    }

    @Override
    public int getItemCount() {
        return biryaniDataModelList.size();
    }

    static public class BurgerAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, department ;
        public BurgerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.biryanicardimg);
            name = itemView.findViewById(R.id.item_name);
        }
    }
}



