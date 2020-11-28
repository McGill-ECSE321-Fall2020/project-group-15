package com.artsee.artsee_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void login(View v) {
        //reset error and customer class
        error = "";
        Customer.resetCustomer();
        final TextView username = (TextView) findViewById(R.id.username_input);
        final TextView password = (TextView) findViewById(R.id.password_input);

        final String[] customerType = {""};
        if(username.getText().toString() == null || username.getText().toString().length() == 0 || password.getText().toString() == null || password.getText().toString().length() == 0){
            error += "Username and/or password cannot be empty";
            refreshErrorMessage();
        } else {
            //create a request params with the inputed username and password
            RequestParams params = new RequestParams();
            params.put("userID", username.getText().toString());
            params.put("password", password.getText().toString());
            params.setUseJsonStreamer(true);

            HttpUtils.post("signIn/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        customerType[0] = response.get("type").toString();
                        //verify if user type is a customer before passing the data into customer class
                        if(customerType[0].equals("Customer")){
                            Customer.initialize(response.get("userID").toString(), response.get("email").toString(), response.get("firstName").toString(), response.get("lastName").toString());
                        }
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }

                    //verify is user type is a customer before navigating to the gallery
                    if(customerType[0].equals("Customer")){
                        username.setText("");
                        password.setText("");
                        Intent myIntent = new Intent(MainActivity.this, ViewGalleryActivity.class);
                        MainActivity.this.startActivity(myIntent);
                    } else if(customerType[0].equals("Administrator") || customerType[0].equals("Artist")) {
                        error+= "Artist and Administrator can only login through the web application.";
                    }

                    refreshErrorMessage();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                    error += errorResponse;
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
            setContentView(R.layout.login_page);
        }

        if (id == R.id.action_gallery) {
            // this is what will bring you to the gallery

            Intent myIntent = new Intent(this, ViewGalleryActivity.class);
            this.startActivity(myIntent);

//            setContentView(R.layout.login_page);
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);
        System.out.println(error);
        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}