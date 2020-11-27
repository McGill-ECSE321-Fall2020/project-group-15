package com.artsee.artsee_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

/**
 * Implements the detailed artwork page from gallery click.
 */
public class DetailedArtworkActivity extends AppCompatActivity {

    private TextView tvName, tvArtistName, tvPrice, tvDescription, tvDate, tvNumInStock, tvArtistDescription;
    private Integer artworkID;
    private ImageView img;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_artwork_nav_bar);

        // declare text and image views
        tvName = (TextView) findViewById(R.id.artwork_title);
        tvArtistName = (TextView) findViewById(R.id.artist_name_text);
        tvPrice = (TextView) findViewById(R.id.artwork_price);
        img = (ImageView) findViewById(R.id.imageView);
        tvDescription = (TextView) findViewById(R.id.artwork_description);
        tvDate = (TextView) findViewById(R.id.artwork_date);
//        tvNumInStock = (TextView) findViewById(R.id.artwork_numInStock);
        tvArtistDescription = (TextView) findViewById(R.id.artist_description);

        // Receive data
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        String description = intent.getExtras().getString("description");
        String date = intent.getExtras().getString("date");
        int numInStock = intent.getExtras().getInt("numInStock");
        String artistName =  intent.getExtras().getString("ArtistName");
        price = Integer.toString(intent.getExtras().getInt("Price") / 100);
        String url = intent.getExtras().getString("Url");
        artworkID = intent.getExtras().getInt("artworkID");
        String artistDescription = intent.getExtras().getString("artistDescription");

        // Setting values
        tvName.setText(name);
        tvArtistName.setText("By "+ artistName);
        tvPrice.setText("$" + price);
        tvDescription.setText(description);
        tvDate.setText(date);
//        tvNumInStock.setText(numInStock);
        tvArtistDescription.setText(artistDescription);

        // convert url to image
        new DownloadImageTask(img).execute(url);

    }


    /**
     * A class to create an asyncTask for a separate thread to download the
     *  images because the main thread crashes on image rendering.
     */
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bitImage;

        public DownloadImageTask(ImageView bitImage) {
            this.bitImage = bitImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mBmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mBmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mBmp;
        }

        protected void onPostExecute(Bitmap result) {
            bitImage.setImageBitmap(result);
        }
    }

    public void purchaseItem(View v){
        // Takes you to the checkout page and passes artworkID parameter

        Intent myIntent = new Intent(DetailedArtworkActivity.this, CheckoutActivity.class);
        myIntent.putExtra("artworkID", artworkID);
        myIntent.putExtra("price", price);
        DetailedArtworkActivity.this.startActivity(myIntent);
    }

}
