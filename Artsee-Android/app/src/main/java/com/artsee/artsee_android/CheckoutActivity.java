package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        artworkID = intent.getExtras().getInt("artworkID");
        price = intent.getExtras().getString("price");

        TextView tvCheckoutPrice;
        tvCheckoutPrice = (TextView) findViewById(R.id.checkout_price);
        tvCheckoutPrice.setText("Price: $" + price);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void checkoutItem(View v){
        error = "";
        TextView tvCardNumber, tvCVV, tvMonth, tvYear;

        // Verifying form info is valid
        TextView tvFirstName = (TextView) findViewById(R.id.card_first_name);
        TextView tvLastName = (TextView) findViewById(R.id.card_last_name);
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

        if(!error.isEmpty()){
            refreshErrorMessage();
        } else {
            // List of artworks
            Artwork[] artworks = new Artwork[1];
            artworks[0] = new Artwork(artworkID);

            // Get shipping option
            ToggleButton deliveryOption = (ToggleButton) findViewById(R.id.toggleButton);
            String deliveryMethodDto = deliveryOption.getText().toString().toUpperCase();

            System.out.println("=======================================");
            System.out.println(Customer.getInstance().getUserID());
            System.out.println("=======================================");

            String userID = Customer.getInstance().getUserID();

            RequestParams params = new RequestParams();
            params.setUseJsonStreamer(true);

            HttpUtils.post("artworkOrders/" + userID +"/"+artworkID.toString()+"/"+deliveryMethodDto,  params, new JsonHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    tvCardNumber.setText("");
                    tvCVV.setText("");
                    tvMonth.setText("");
                    tvYear.setText("");
                    tvYear.setText("");

                    tvFirstName.setText("");
                    tvLastName.setText("");

                    error = "";

                    refreshErrorMessage();

                    Intent myIntent = new Intent(CheckoutActivity.this, ThankYouActivity.class);
                    CheckoutActivity.this.startActivity(myIntent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    error += errorResponse.toString();
                    refreshErrorMessage();
                }
            });
        }




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

    private void refreshErrorMessage() {

//         ADD ERROR FIELD AND MAKE MESSAGE SHOW

        // set the error message
        TextView tvError = (TextView) findViewById(R.id.errorCheckout);
        tvError.setText(error);
        System.out.println(error);
        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
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