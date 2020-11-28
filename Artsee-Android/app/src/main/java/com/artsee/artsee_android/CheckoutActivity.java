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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CheckoutActivity extends AppCompatActivity {
    private Integer artworkID;
    private String price;
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_navbar);
        Intent intent = getIntent();
        artworkID = intent.getExtras().getInt("artworkID");
        price = intent.getExtras().getString("price");

        TextView tvCheckoutPrice;
        tvCheckoutPrice = (TextView) findViewById(R.id.checkout_price);
        tvCheckoutPrice.setText("Price: $" + price);

    }

    public void checkoutItem(View v){
        error = "";
        TextView tvCardNumber, tvCVV, tvMonth, tvYear;

        // Verifying form info is valid
        tvCardNumber = (TextView) findViewById(R.id.card_number);
        tvCVV = (TextView) findViewById(R.id.card_cvv);
        tvMonth = (TextView) findViewById(R.id.card_month);
        tvYear = (TextView) findViewById(R.id.card_year);

        if (tvCardNumber.getText().toString().length()!=16 || !isNumeric(tvCardNumber.getText().toString())){
            error = "Please enter a valid card number";
        } else if (tvCVV.getText().toString().length()!=3 || !isNumeric(tvCVV.getText().toString())){
            error = "Please enter a valid CVV";
        } else if (tvMonth.getText().toString().length()!=2 || !isNumeric(tvMonth.getText().toString())
                || Double.parseDouble((tvMonth.getText().toString())) > 12 || Double.parseDouble((tvMonth.getText().toString())) < 1) {
            error = "Please enter a valid month";
        } else if (tvYear.getText().toString().length()!=4 || !isNumeric(tvYear.getText().toString())
                || Double.parseDouble((tvYear.getText().toString())) < 2020) {
            error = "Please enter a valid year";
        }

        // Setting the parameters to pass to the http body for order
        RequestParams params = new RequestParams();
//        RequestParams customer = new RequestParams();
        //        customer.put("userID",Customer.getInstance().getUserID());
//        RequestParams params2 = new RequestParams();

//        JSONObject customer = new JSONObject();
//        JSONArray artworks = new JSONArray();
//        JSONObject order = new JSONObject();
//        try{
//
//            customer.put("userID","john");
//            order.put("artworks", artworks);
//            order.put("deliveryMethodDto", "SHIP");
//
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        RequestParams requestParams = JsonHelper.toRequestParams(params);


        // List of artworks
//        ArrayList<Artwork> artworks = new ArrayList<Artwork>();
        Artwork[] artworks = new Artwork[0];
//        artworks.add(new Artwork(artworkID));
        params.put("artworks", artworks);

        // Customer placing the order
//        CustomerDto customer = new CustomerDto(Customer.getInstance().getUserID());
//        params.put("customer", customer);

        // Get shipping option
        ToggleButton deliveryOption = (ToggleButton) findViewById(R.id.toggleButton);
        String deliveryMethodDto = deliveryOption.getText().toString().toUpperCase();
        params.put("deliveryMethodDto", ArtworkOrder.DeliveryMethodDto.SHIP);


//        RequestParams orderParam = new RequestParams();
//        ArtworkOrder order = new ArtworkOrder(ArtworkOrder.DeliveryMethodDto.SHIP, customer, artworks);
//        orderParam.put("artworkOrderDto",order);
//        orderParam.setUseJsonStreamer(true);
//
//        System.out.println("=======================================");
//        System.out.println("params: " + params.toString());
//        System.out.println("=======================================");

        params.setUseJsonStreamer(true);


        JSONObject jsonParams = new JSONObject();
        JSONObject customer = new JSONObject();
        StringEntity entity = null;

        try{
            customer.put("userID", "john");
            jsonParams.put("artworks", new ArrayList<Artwork>());
            jsonParams.put("deliveryMethodDto", ArtworkOrder.DeliveryMethodDto.SHIP);
             entity = new StringEntity(jsonParams.toString());
        } catch (Exception e){
            System.out.println("=======================================");
            System.out.println(e.getMessage());
            System.out.println("=======================================");
        }




        HttpUtils.post(this, "artworkOrders", entity, "application/json", new JsonHttpResponseHandler() {
//        HttpUtils.post("artworkOrders/", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                System.out.println("=======================================");
                System.out.println("In Success");
                System.out.println("=======================================");

                try {

                    System.out.println("Success");
                    // Parse response if there is one
                    //Redirect to order placed page

//                    Intent myIntent = new Intent(MainActivity.this, ViewGalleryActivity.class);
//                    MainActivity.this.startActivity(myIntent);

                } catch (Exception e) {
                    error += e.getMessage();
                }

//                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("================================");
                System.out.println("Failure in place order: "+ errorResponse.toString());
                try{
                    System.out.println(errorResponse.getString("error"));
                } catch (Exception e){
                    System.out.println("Error below from catch");
                    System.out.println(e.getMessage());
                }

//                System.out.println("Customer: " + customer.getUserID());
//                System.out.println("Artwork: " + artworks.get(0).getID());
                System.out.println("Delivery: " + deliveryMethodDto);
                System.out.println("================================");

                //                error += errorResponse;
                //                refreshErrorMessage();
            }

//            @Override
//            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
//                System.out.println("================================");
//                System.out.println("Failure in place order: "+ error);
////                System.out.println(orderParam.toString());
//                System.out.println("================================");
//
//                //                error += errorResponse;
//                //                refreshErrorMessage();
//            }
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

    // Checks if a string is a number
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
