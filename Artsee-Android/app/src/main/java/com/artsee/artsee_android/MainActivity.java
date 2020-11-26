package com.artsee.artsee_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        final TextView username = (TextView) findViewById(R.id.username_input);
        final TextView password = (TextView) findViewById(R.id.password_input);
        final String usernameString = username.getText().toString();
        final String passwordString = password.getText().toString();
        if(usernameString == null || usernameString.length() == 0 || passwordString == null || passwordString.length() == 0){

        } else {
            RequestParams params = new RequestParams();
            params.put("userID", usernameString);
            params.put("password", passwordString);
            params.setUseJsonStreamer(true);

            HttpUtils.post("signIn/", params, new JsonHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        setContentView(R.layout.detailed_artwork_nav_bar);
                        Toolbar toolbar = findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);

                        System.out.println("pass1");
                        System.out.println(response.get("type").toString());
                    } catch (JSONException e) {
                        System.out.println("pass2");
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        System.out.println("fail1");
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        System.out.println("fail2");
                        error += e.getMessage();
                    }
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
            setContentView(R.layout.login_page);
        }

        if (id == R.id.action_gallery) {
            // this is what will bring you to the gallery
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}