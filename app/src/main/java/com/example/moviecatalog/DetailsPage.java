package com.example.moviecatalog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsPage extends AppCompatActivity {

    ArrayList<String> cast;
    TextView cast_txt;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        this.setTitle("Details");
        cast = new ArrayList<>();
        requestQueue = Singelton.getSingletonInstance(this).getRequestQueue();
        ImageView cover = findViewById(R.id.coverImg);
        ImageView poster = findViewById(R.id.photo);
        TextView title_txt = findViewById(R.id.textview);
        TextView rating_txt = findViewById(R.id.textviewrate);
        TextView yearOfRelease_txt = findViewById(R.id.date);
        TextView description_txt = findViewById(R.id.descTxt);
        cast_txt = findViewById(R.id.cast);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String movieTitle = bundle.getString("title");
        String movie_cover = bundle.getString("cover");
        String movie_poster = bundle.getString("poster");
        Double movie_rating = bundle.getDouble("rating");
        String yearOfRelease = bundle.getString("date");
        String movieDescription = bundle.getString("description");
        Glide.with(this).load("https://image.tmdb.org/t/p/original/" + movie_cover).into(cover);
        Glide.with(this).load("https://image.tmdb.org/t/p/original/" + movie_poster).into(poster);
        title_txt.setText(movieTitle);
        rating_txt.setText(Double.toString(movie_rating));
        yearOfRelease_txt.setText(yearOfRelease);
        description_txt.setText(movieDescription);

        fetchCast(id);
    }

    private void fetchCast(int id){
        String cast = "https://api.themoviedb.org/3/movie/" + id +
                "/credits?api_key=67481520fb4166fcee4829ac81476698&language=en-US";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, cast, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("cast");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        DetailsPage.this.cast.add(name);
                    }
                    cast_txt.setText(DetailsPage.this.cast.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}