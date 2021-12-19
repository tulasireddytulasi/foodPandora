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

public class BiryaniAdapter extends RecyclerView.Adapter<BiryaniAdapter.BiryaniAdapterViewHolder> {

    private Context context;
    private List<BiryaniDataModel> biryaniDataModelList;
    public BiryaniAdapter(Context context, List<BiryaniDataModel> biryaniDataModelList) {
        this.context = context;
        this.biryaniDataModelList = biryaniDataModelList;
    }

    @NonNull
    @Override
    public BiryaniAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biryanilist_layout, parent, false);
        return new BiryaniAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiryaniAdapter.BiryaniAdapterViewHolder holder, int position) {

      //  MoviePage1 moviePage = moviePage1;
        List<BiryaniDataModel> biryaniDataModelList5 = biryaniDataModelList;

        Glide.with(context)
                .load("https://marvelwallpapers.000webhostapp.com/"+biryaniDataModelList5.get(position).getName())
                .placeholder(R.drawable.poster1)
                .centerCrop()
                .into(holder.imageView);

//        holder.name.setText(moviePage.getCredits().getCast().get(position).getCharacter());
//        holder.department.setText(moviePage.getCredits().getCast().get(position).getOriginal_name());
    }

    @Override
    public int getItemCount() {
        return biryaniDataModelList.size();
    }

    public class BiryaniAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, department ;
        public BiryaniAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.biryanicardimg);
//            name = itemView.findViewById(R.id.name);
//            department = itemView.findViewById(R.id.department);
        }
    }
}



