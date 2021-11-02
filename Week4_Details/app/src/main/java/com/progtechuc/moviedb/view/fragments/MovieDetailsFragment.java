package com.progtechuc.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.Genre;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_movie_title, lbl_movie_date, lbl_movie_description, lbl_movie_vote,
            lbl_movie_popularity, lbl_movie_score;
    private ImageView img_poster_moviedetails, img_backdrop_moviedetails;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Genre genre = new Genre();

        String movieId = getArguments().getString("movieId");
        String movieTitle = getArguments().getString("movieTitle");
        String movieDate = getArguments().getString("movieDate");
        String movieDescription = getArguments().getString("movieDescription");
        String movieVote = getArguments().getString("movieVote");
        String moviePopularity = getArguments().getString("moviePopularity");
        String movieAvg = getArguments().getString("movieAvg");

        lbl_movie_title = view.findViewById(R.id.textView_title_moviedetails_fragment);
        lbl_movie_date = view.findViewById(R.id.textView_date_moviedetails_fragment);
        lbl_movie_description = view.findViewById(R.id.textView_description_moviedetails_fragment);
        lbl_movie_vote = view.findViewById(R.id.textView_vote_moviedetails_fragment);
        lbl_movie_popularity = view.findViewById(R.id.textView_popularity_moviedetails_fragment);
        lbl_movie_score = view.findViewById(R.id.textView_score_moviedetails_fragment);
        img_backdrop_moviedetails = view.findViewById(R.id.img_backdrop_moviedetails_fragment);
        img_poster_moviedetails = view.findViewById(R.id.img_poster_moviedetails_fragment);

        movieViewModel = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);
        movieViewModel.getMovieById(movieId);
        movieViewModel.getResultGetMovieById().observe(getViewLifecycleOwner(), showMovieDetail);
        movieViewModel.getResultGetMovieById().observe(getViewLifecycleOwner(), backDropDetail);


        lbl_movie_title.setText(movieTitle);
        lbl_movie_date.setText(movieDate);
        lbl_movie_description.setText(movieDescription);
        lbl_movie_vote.setText(movieVote);
        lbl_movie_popularity.setText(moviePopularity);
        lbl_movie_score.setText(movieAvg);

        return view;
    }

    private Observer<Movies> showMovieDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String poster_path = movies.getPoster_path().toString();
            if (!(poster_path == null)) {
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(MovieDetailsFragment.this)
                        .load(full_path)
                        .into(img_poster_moviedetails);
            }

        }

    };

    private Observer<Movies> backDropDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String backdrop_path = movies.getBackdrop_path().toString();
            if (!(backdrop_path == null)) {
                String full_path = Const.IMG_URL + backdrop_path;
                Glide.with(MovieDetailsFragment.this)
                        .load(full_path)
                        .into(img_backdrop_moviedetails);
            }
        }
    };

}