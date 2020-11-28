package com.artsee.artsee_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.InputStream;

/**
 * Implements the detailed artwork page from gallery click.
 */
public class DetailedArtworkActivity extends AppCompatActivity {

    private TextView tvName, tvArtistName, tvPrice, tvDescription, tvDate, tvNumInStock, tvArtistDescription;
    private Integer artworkID;
    private ImageView img;
    private String price;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_artwork_nav_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // declare text and image views
        tvName = (TextView) findViewById(R.id.artwork_title);
        tvArtistName = (TextView) findViewById(R.id.artist_name_text);
        tvPrice = (TextView) findViewById(R.id.artwork_price);
        img = (ImageView) findViewById(R.id.imageView);
        tvDescription = (TextView) findViewById(R.id.artwork_description);
        tvDate = (TextView) findViewById(R.id.artwork_date);
        tvArtistDescription = (TextView) findViewById(R.id.artist_description);

        // Receive data
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        String description = intent.getExtras().getString("description");
        String date = intent.getExtras().getString("date");
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
        tvArtistDescription.setText(artistDescription);

        // convert url to image
        new DownloadImageTask(img).execute(url);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * A class to create an asyncTask for a separate thread to download the
     *  images because the main thread crashes on image rendering.
     */
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bitImage;

        /**
         *
         * @param bitImage
         */
        public DownloadImageTask(ImageView bitImage) {
            this.bitImage = bitImage;
        }

        /**
         *
         * @param urls
         * @return
         */
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

        /**
         *
         * @param result
         */
        protected void onPostExecute(Bitmap result) {
            bitImage.setImageBitmap(result);
        }
    }

    /**
     * handle purchase item
     * @param v
     */
    public void purchaseItem(View v){
        // Takes you to the checkout page and passes artworkID parameter

        Intent myIntent = new Intent(DetailedArtworkActivity.this, CheckoutActivity.class);
        myIntent.putExtra("artworkID", artworkID);
        myIntent.putExtra("price", price);
        DetailedArtworkActivity.this.startActivity(myIntent);
    }


    /**
     * handles navigation buttons
     * @param item
     * @return
     */
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
            Intent myIntent = new Intent(this, MainActivity.class);
            this.startActivity(myIntent);
        }

        if (id == R.id.action_gallery) {
            // this is what will bring you to the gallery
            Intent myIntent = new Intent(this, ViewGalleryActivity.class);
            this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
