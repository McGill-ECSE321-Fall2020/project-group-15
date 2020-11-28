package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Thank you activity class
 */
public class ThankYouActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_page);
    }

    /**
     * Redirect the thank you page to the gallery
     * @param v
     */
    public void redirectGallery(View v){
        Intent myIntent = new Intent(ThankYouActivity.this, ViewGalleryActivity.class);
        ThankYouActivity.this.startActivity(myIntent);
    }
}



