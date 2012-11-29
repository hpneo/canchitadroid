package com.canchita.android;

import java.util.ArrayList;

import com.canchita.android.models.Movie;
import com.canchita.android.tasks.MoviesTask;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.markupartist.android.widget.actionbar.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends FragmentActivity {
  
    private ActionBar actionBar;
    private MovieAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
      setContentView(R.layout.activity_main);
      super.onCreate(savedInstanceState);
      this.actionBar = (ActionBar) findViewById(R.id.actionbar);
      this.actionBar.setHomeAction(new IntentAction(this, MainActivity.createIntent(this), R.drawable.ic_launcher));
      
      GridView movieGridView = (GridView)findViewById(R.id.gridViewMovies);
      
      this.listAdapter = new MovieAdapter(this, R.layout.movie_item, new ArrayList<Movie>());
      this.listAdapter.setNotifyOnChange(true);
      
      if(movieGridView != null) {
        movieGridView.setAdapter(this.listAdapter);
        //movieGridView.setOnItemClickListener(new MovieItemClickListener());
      }
      
      if(CachedData.getMovies() == null) {
        MoviesTask moviesTask = new MoviesTask(this, this.getApplicationContext());
        moviesTask.execute("http://canchita.jelastic.servint.net/api/movies");
      }
      else {
        this.updateMoviesList(CachedData.getMovies());
      }
    }
    
    public static Intent createIntent(Context context) {
      Intent intent = new Intent(context, MainActivity.class);
      
      return intent;
    }
    
    public void updateMoviesList(ArrayList<Movie> movies) {
      int count = movies.size();
      
      if(count > 0) {
        this.listAdapter.clear();
        CachedData.setMovies(movies);
        for(int i = 0; i < count; i++) {
          this.listAdapter.add(movies.get(i));
        }
      }
    }
}
