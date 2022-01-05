package com.example.mo_v.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mo_v.R;
import com.example.mo_v.models.MovieModel;
import com.example.mo_v.utils.Credentials;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OmMovieListener omMovieListener;

    private static final int DISPLAY_POPUALAR=1;
    private static final int DISPLAY_SEARCH=2;


    public MovieRecyclerView(OmMovieListener omMovieListener) {
        this.omMovieListener = omMovieListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == DISPLAY_SEARCH) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

            return new MovieViewHolder(view, omMovieListener);
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_list_item, parent, false);
        return new MovieViewHolder(view, omMovieListener);
    }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       int itemtype=getItemViewType(position);
       if(itemtype==DISPLAY_SEARCH){
           ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(position).getVote_average())/2);
           Glide.with(holder.itemView.getContext())
                   .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(position).getPoster_path())
                   .into(((MovieViewHolder)holder).movieimg);
       }
       else{
           ((PopulaViewHolder)holder).ratingBar22.setRating((mMovies.get(position).getVote_average())/2);
           Glide.with(holder.itemView.getContext())
                   .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(position).getPoster_path())
                   .into(((PopulaViewHolder)holder).movieimg22);

        }


    }

    @Override
    public int getItemCount() {
        if(mMovies!=null){
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
            this.mMovies = mMovies;
            notifyDataSetChanged();

    }

    public MovieModel getSelectedMovie(int position){

        if(mMovies!=null){
            if( mMovies.size()>0){
                return mMovies.get(position);
            }
        }
        return null;

    }


    @Override
    public int getItemViewType(int position) {
        if(Credentials.Popular){
            return DISPLAY_POPUALAR;

        }else  return DISPLAY_SEARCH;
    }




    /* public void clear() {
        int size = mMovies.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mMovies.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }*/
}
