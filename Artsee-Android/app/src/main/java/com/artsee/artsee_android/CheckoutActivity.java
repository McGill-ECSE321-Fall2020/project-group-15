package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    private Integer artworkID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_navbar);
        Intent intent = getIntent();
        Integer artworkID = intent.getExtras().getInt("artworkID");

        System.out.println("================================");
        System.out.println(artworkID);
        System.out.println("================================");

    }
}
