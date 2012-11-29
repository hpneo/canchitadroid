package com.canchita.android.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private String poster;
  
  public static Movie fromJSON(JSONObject object) {
    Movie movie = new Movie();
    
    try {
      movie.setName(object.getString("name"));
      movie.setPoster("http://canchita.jelastic.servint.net/javax.faces.resource/" + object.getString("poster") + ".xhtml?ln=images/thumbs");
    } catch (Exception e) {
    }
    
    return movie;
  }
  
  public static ArrayList<Movie> fromJSON(JSONArray json_collection) {
    ArrayList<Movie> collection = new ArrayList<Movie>();
    
    int count = json_collection.length();
    int i = 0;
    
    try {
      for(i = 0; i < count; i++) {
        Movie product = Movie.fromJSON((JSONObject)json_collection.get(i));
        
        collection.add(product);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return collection;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPoster() {
    return poster;
  }
  public void setPoster(String poster) {
    this.poster = poster;
  }

}
