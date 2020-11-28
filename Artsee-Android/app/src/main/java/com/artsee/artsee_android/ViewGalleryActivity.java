package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ViewGalleryActivity extends AppCompatActivity {

    private List<Artwork> artworks = new ArrayList<Artwork>();
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_gallery);
        setContentView(R.layout.activity_view_gallery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initArtworks();
        initRecyclerView();

    }

        private void initArtworks(){
        // Get request to fill gallery

        HttpUtils.get("artworks/",  new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Clear past artworks
                artworks.clear();
                Artwork artwork;
                JSONObject data;
                String url;

                for(int i = 0; i < response.length(); i++){
                    // Get artworks from JSOn

                    try{
                        data = response.getJSONObject(i);
                        JSONObject artistResponse = (JSONObject) data.get("artist");

                        // Create artist object based on JSON
                        Artist artist = new Artist(artistResponse.getString("userID"), artistResponse.getString("email"), artistResponse.getString("firstName"), artistResponse.getString("lastName"), artistResponse.getString("phoneNumber"), artistResponse.getString("artistDescription"), (Double) artistResponse.get("rating"), artistResponse.getString("profilePictureURL"));

                        url = data.getString("imageURL");
                        if (url == null || url.isEmpty()){
                            // If no image exists, set default image
                             url = "https://icon-library.com/images/no-image-icon/no-image-icon-1.jpg";
                        }

                        //Create artwork object based on JSON
                        artwork = new Artwork((Integer) data.get("id"), data.getString("name"), data.getString("description"), data.getInt("price"), data.getString("dateOfCreation"), data.getInt("numInStock"), artist, url);

                        //Add artwork to list to be rendered
                        artworks.add(artwork);

                        initRecyclerView();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        error += e.getMessage();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });

    }
    private void initRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_id);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(this, artworks);

        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            // logout of the application
            Customer.resetCustomer();
            
            setContentView(R.layout.login_page);
        }

        if (id == R.id.action_gallery) {
            // this is what will bring you to the gallery

            Intent myIntent = new Intent(this, ViewGalleryActivity.class);
            this.startActivity(myIntent);

//            setContentView(R.layout.login_page);
        }

        return super.onOptionsItemSelected(item);
    }


}
