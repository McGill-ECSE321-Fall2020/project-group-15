package com.artsee.artsee_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.Header;


public class ViewGalleryActivity extends AppCompatActivity {

    private List<Artwork> artworks = new ArrayList<Artwork>();
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gallery);
        initArtworks();

//        artworks = getAllArtworks();
//        artworks.add(new Artwork("Mona Lisa", 14,
//                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Leonardo_da_Vinci_043-mod.jpg/330px-Leonardo_da_Vinci_043-mod.jpg",
//                new Artist("Adrien")));
//        artworks.add(new Artwork("white", 12,
//                "https://thumbs.dreamstime.com/z/abstract-art-painting-white-gray-color-texture-background-ideas-148073133.jpg",
//                new Artist("Bob")));
//        artworks.add(new Artwork("new", 10,
//                "https://i.cbc.ca/1.5387286.1575662626!/fileImage/httpImage/image.jpg_gen/derivatives/original_1180/van-gogh-immersive-art.jpg",
//                new Artist("John")));
//        artworks.add(new Artwork("Simple", 20,
//                "https://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2013/2/11/2/RX-HGMAG008_Dont-Buy-It-DIY-It-084-a_s4x3.jpg.rend.hgtvcom.966.725.suffix/1400979946360.jpeg",
//                new Artist("Jack")));
//        artworks.add(new Artwork("Recent art", 24,
//                "https://www.artranked.com/images/s_d3/d321bbcbd45eb95ab0dedf766cb7a742.jpeg",
//                new Artist("Gareth")));
//
//        initRecyclerView();
    }

        private void initArtworks(){
        // Get request to fill gallery
            System.out.println("Inside the init artowrks");
            System.out.println("=============================================");

        HttpUtils.get("artworks/",  new RequestParams(), new JsonHttpResponseHandler() {

//            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("Inside the loop");
                System.out.println("=============================================");
            }

//            @Override
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
}
