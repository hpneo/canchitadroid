package com.canchita.android.tasks;

import java.util.ArrayList;

import com.canchita.android.MainActivity;
import com.canchita.android.models.Movie;
import com.canchita.android.utils.*;
import com.canchita.android.utils.RestClient.RequestMethod;

import org.json.*;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class MoviesTask extends AsyncTask<String, Integer, String> {

  protected MainActivity parentFragment;
  protected Context applicationContext;
  private ProgressDialog dialog;
  
  public MoviesTask(MainActivity parentFragment, Context applicationContext) {
    this.parentFragment = parentFragment;
    this.applicationContext = parentFragment;
    this.dialog = new ProgressDialog(this.applicationContext);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    this.dialog.setTitle("Loading movies...");
    this.dialog.setMessage("Wait...");
    this.dialog.show();
  }

  @Override
  protected String doInBackground(String... params) {
    String url = params[0];
    String response = "";
    
    RestClient client = new RestClient(url);
    
    try {
      System.out.println(url);
      client.Execute(RequestMethod.GET);
    } catch (Exception e) {
      System.out.println("Exception ==========================");
      System.out.println(e.getMessage());
      System.out.println("====================================");
    }

    response = client.getResponse();
    
    return response;
  }
  
  @Override
  protected void onPostExecute(String result) {
    super.onPostExecute(result);
    
    try {
      JSONArray movies_result = new JSONArray(result);
      
      ArrayList<Movie> movies = Movie.fromJSON(movies_result);
      
      this.parentFragment.updateMoviesList(movies);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.dialog.dismiss();
  }

}
