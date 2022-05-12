package com.example.mo_v;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mo_v.adapter.MovieViewHolder;
import com.example.mo_v.models.MovieModel;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title, description, release_date;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imageid);
        title = findViewById(R.id.textView_title);
        description = findViewById(R.id.description);
        ratingBar = findViewById(R.id.d_ratingBar);
        release_date=findViewById(R.id.release_datee);

        GetDataFromIntent();


    }

    private void GetDataFromIntent() {

        if (getIntent().hasExtra("movie")) {

            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            title.setText(movieModel.getTitle());
            description.setText(movieModel.getOverview());
            description.setMovementMethod(new ScrollingMovementMethod());
            release_date.setText(movieModel.getRelease_date());
            ratingBar.setRating((movieModel.getVote_average()) / 2);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path())
                    .into(imageView);

        }

    }
}