package com.canchita.android;

import java.util.*;

import com.canchita.android.models.*;

public class CachedData {

  private static ArrayList<Movie> movies = null;
  
  public static ArrayList<Movie> getMovies() {
    return movies;
  }
  
  public static void setMovies(ArrayList<Movie> movies) {
    CachedData.movies = movies;
  }

}
