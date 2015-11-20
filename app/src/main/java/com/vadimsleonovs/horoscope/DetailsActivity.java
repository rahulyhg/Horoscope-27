package com.vadimsleonovs.horoscope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    TextView mDateTextView;
    TextView mHoroscopeTextView;
    TextView mSignTextView;
    int mExtra;
    Intent mIntent;


    //Volley stuff
    RequestQueue requestQueue;
    String mToday = "today/";
    String mWeek = "week/";
    String mMonth = "month/";
    String mYear = "year/";
    String mUrl = "http://horoscope-api.herokuapp.com/horoscope/";
    String mDate = "date";
    String mHoroscope = "horoscope";
    String mSunsign = "sunsign";
    String mMember;


    //Received stuff
    String mDailyHoroscope = "";
    String mZodiacSign = "";
    String mHoroscopeDate = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        mDateTextView = (TextView) findViewById(R.id.date_label);
        mHoroscopeTextView = (TextView) findViewById(R.id.horoscope_label);
        mSignTextView = (TextView) findViewById(R.id.zodiac_label);

        mIntent = getIntent();
        mExtra =  mIntent.getIntExtra(HomeActivity.INTENT_KEY, 1);
        mMember = getResources().getResourceEntryName(mExtra);
        requestQueue = Volley.newRequestQueue(this);
        makeDailyHoroscope();




        //mTextView.setText(member);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void makeDailyHoroscope(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrl + mToday + mMember,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mDailyHoroscope = response.getString(mHoroscope);
                            mZodiacSign = response.getString(mSunsign);
                            mHoroscopeDate = response.getString(mDate);

                            mDateTextView.setText(mHoroscopeDate);
                            mHoroscopeTextView.setText(mDailyHoroscope);
                            mSignTextView.setText(mZodiacSign);
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        );
        requestQueue.add(jsonObjectRequest);

    }
}
