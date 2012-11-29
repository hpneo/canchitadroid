package com.canchita.android;

import java.math.BigDecimal;
import java.util.*;

import com.canchita.android.models.*;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class MovieAdapter extends ArrayAdapter<Movie> {
  private ArrayList<Movie> movies;

  public MovieAdapter(Context context, int textViewResourceId, ArrayList<Movie> collection) {
    super(context, textViewResourceId, collection);
    this.movies = collection;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    
    if(view == null) {
      LayoutInflater view_inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = view_inflater.inflate(R.layout.movie_item, null);
    }

    Movie movie = this.movies.get(position);
    
    if(movie != null) {
      SmartImageView movie_thumb = (SmartImageView)view.findViewById(R.id.movie_thumb);
      movie_thumb.setImageUrl(movie.getPoster());
      
      TextView movie_name = (TextView)view.findViewById(R.id.movie_name);
      movie_name.setText(movie.getName());
    }
    
    return view;
  }
}
