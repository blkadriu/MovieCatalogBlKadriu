package com.example.moviecatalog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MovieHolder> {

    private Context context;
    private List<com.example.moviecatalog.Movie> movieList;

    public CustomAdapter(Context context, List<com.example.moviecatalog.Movie> movies) {
        this.context = context;
        this.movieList = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        com.example.moviecatalog.Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getRating().toString());
        holder.dateOfRelease.setText(movie.getDateOfRelease());
        holder.description.setText(movie.getDescription());

        Glide.with(context).load("https://image.tmdb.org/t/p/original/" + movie.getPoster()).into(holder.posterImage);

        holder.constraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsPage.class);

            Bundle bundle = new Bundle();
            bundle.putString("title", movie.getTitle());
            bundle.putDouble("rating", movie.getRating());
            bundle.putString("cover", movie.getCover());
            bundle.putString("poster", movie.getPoster());
            bundle.putString("date", movie.getDateOfRelease());

            bundle.putString("description", movie.getDescription());
            bundle.putInt("id", movie.getId());

            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        ImageView posterImage;
        TextView title, rating, dateOfRelease, description;

        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            posterImage = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.movie_title);
            rating = itemView.findViewById(R.id.movie_rating);
            dateOfRelease = itemView.findViewById(R.id.date1);
            description = itemView.findViewById(R.id.movie_dsc);

            constraintLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
