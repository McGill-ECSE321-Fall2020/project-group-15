package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CheckoutActivity extends AppCompatActivity {
    private Integer artworkID;
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_navbar);
        Intent intent = getIntent();
        artworkID = intent.getExtras().getInt("artworkID");

        System.out.println("================================");
        System.out.println(artworkID);
        System.out.println("================================");

    }

    public void checkoutItem(View v){
        error = "";

        // Setting the parameters to pass to the http body for order
        RequestParams params = new RequestParams();

        // List of artworks
        List<Artwork> artworks = new ArrayList<Artwork>();
        artworks.add(new Artwork(artworkID));
        params.put("artworks", artworks);

        // Customer placing the order
        CustomerDto customer = new CustomerDto(Customer.getInstance().getUserID());
        params.put("customer", customer);

        // Get shipping option
        ToggleButton deliveryOption = (ToggleButton) findViewById(R.id.toggleButton);
        String deliveryMethodDto = deliveryOption.getText().toString().toUpperCase();

//        if (deliveryOption.getText().toString().equalsIgnoreCase("ship")){
//            deliveryMethodDto = ArtworkOrder.DeliveryMethodDto.SHIP;
//
//        } else if (deliveryOption.getText().toString().equalsIgnoreCase("pickup")){
//            deliveryMethodDto = ArtworkOrder.DeliveryMethodDto.PICKUP;
//
//        } else {
//            System.out.println("=======================================");
//            System.out.println("Error with understnading delivery option");
//            System.out.println("=======================================");
//
//        }

        params.put("deliveryMethodDto", deliveryMethodDto);

        params.setUseJsonStreamer(true);


        //CHECK THAT THE PARAMS AND ALL FIELDS ARE VALID

        HttpUtils.post("artworkOrders/", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {

                    // Parse response if there is one
                    //Redirect to order placed page

//                    Intent myIntent = new Intent(MainActivity.this, ViewGalleryActivity.class);
//                    MainActivity.this.startActivity(myIntent);

                } catch (Exception e) {
                    error += e.getMessage();
                }

                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("================================");
                System.out.println("Failure in place order: "+ errorResponse.toString());
                System.out.println("Customer: " + customer.getUserID());
                System.out.println("Artwork: " + artworks.get(0).getID());
                System.out.println("Artwork: " + artworks.get(0).getID());
                System.out.println("================================");

                //                error += errorResponse;
//                refreshErrorMessage();
            }
        });
    }

    private void refreshErrorMessage() {

        // ADD ERROR FIELD AND MAKE MESSAGE SHOW

//        // set the error message
//        TextView tvError = (TextView) findViewById(R.id.error);
//        tvError.setText(error);
//        System.out.println(error);
//        if (error == null || error.length() == 0) {
//            tvError.setVisibility(View.GONE);
//        } else {
//            tvError.setVisibility(View.VISIBLE);
//        }
    }
}
