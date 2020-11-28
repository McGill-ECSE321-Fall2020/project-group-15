package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_page);
    }

    public void redirectGallery(View v){
        Intent myIntent = new Intent(ThankYouActivity.this, ViewGalleryActivity.class);
        ThankYouActivity.this.startActivity(myIntent);
    }
}



