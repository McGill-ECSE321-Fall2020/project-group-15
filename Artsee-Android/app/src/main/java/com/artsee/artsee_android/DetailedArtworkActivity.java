package com.artsee.artsee_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

/**
 * Implements the detailed artwork page from gallery click.
 */
public class DetailedArtworkActivity extends AppCompatActivity {

    private TextView tvName, tvArtistName, tvPrice;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_artwork_nav_bar);

        // declare text and image views
        tvName = (TextView) findViewById(R.id.artwork_title);
        tvArtistName = (TextView) findViewById(R.id.artist_name_text);
        tvPrice = (TextView) findViewById(R.id.artwork_price);
        img = (ImageView) findViewById(R.id.imageView);

        // Receive data
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        String artist_name = intent.getExtras().getString("Artist");
        String price = Integer.toString(intent.getExtras().getInt("Price"));
        String url = intent.getExtras().getString("Url");

        // Setting values
        tvName.setText(name);
        tvArtistName.setText("By "+ artist_name);
        tvPrice.setText("$" + price);
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

}
