package com.vadimsleonovs.horoscope;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {


    public static final String INTENT_KEY = "intent_key";
    Button mButtonAquarius;
    Button mButtonGemini;
    Button mButtonLibra;
    Button mButtonAries;
    Button mButtonLeo;
    Button mButtonSagittarius;
    Button mButtonCapricorn;
    Button mButtonTaurus;
    Button mButtonVirgo;
    Button mButtonCancer;
    Button mButtonScorpio;
    Button mButtonPisces;
    DBHelper mDBHelper;


    String TableName = "myTable";
    Animation anim;
    String url = "http://horoscope-api.herokuapp.com";

    private int mYears = 0;
    private int mYearsP = 0;
    private boolean mCheck;
    private SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDBHelper = new DBHelper(this);
        mDBHelper.getWritableDatabase();
        initializeBtns();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void loadPreferences() {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mYears = mSharedPreferences.getInt(SettingsActivity.USER_YEAR, SettingsActivity.mYear);
    }

    void checkPreferences() {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mString = mSharedPreferences.getString(SettingsActivity.FIRST_TIME_CHECK, "true");
        String value = mSharedPreferences.getString("unknown_key", null);
        if (value == null) {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    void loadFragmentPreferences() {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mYearsP = mSharedPreferences.getInt(FragmentSettings.PARTNER_YEAR, FragmentSettings.mYear);
    }

    void initializeBtns() {
        mButtonAquarius = (Button) findViewById(R.id.aquarius);
        mButtonGemini = (Button) findViewById(R.id.gemini);
        mButtonLibra = (Button) findViewById(R.id.libra);

        mButtonAries = (Button) findViewById(R.id.aries);
        mButtonLeo = (Button) findViewById(R.id.leo);
        mButtonSagittarius = (Button) findViewById(R.id.sagittarius);

        mButtonCapricorn = (Button) findViewById(R.id.capricorn);
        mButtonTaurus = (Button) findViewById(R.id.taurus);
        mButtonVirgo = (Button) findViewById(R.id.virgo);

        mButtonCancer = (Button) findViewById(R.id.cancer);
        mButtonScorpio = (Button) findViewById(R.id.scorpio);
        mButtonPisces = (Button) findViewById(R.id.pisces);


        mButtonAquarius.setBackgroundResource(R.drawable.aquarius);
        mButtonGemini.setBackgroundResource(R.drawable.gemini);
        mButtonLibra.setBackgroundResource(R.drawable.libra);

        mButtonAries.setBackgroundResource(R.drawable.aries);
        mButtonLeo.setBackgroundResource(R.drawable.leo);
        mButtonSagittarius.setBackgroundResource(R.drawable.sagittarius);

        mButtonCapricorn.setBackgroundResource(R.drawable.capricorn);
        mButtonTaurus.setBackgroundResource(R.drawable.taurus);
        mButtonVirgo.setBackgroundResource(R.drawable.virgo);

        mButtonCancer.setBackgroundResource(R.drawable.cancer);
        mButtonScorpio.setBackgroundResource(R.drawable.scorpio);
        mButtonPisces.setBackgroundResource(R.drawable.pisces);


        mButtonAquarius.setOnClickListener(mOnClickListener);
        mButtonGemini.setOnClickListener(mOnClickListener);
        mButtonLibra.setOnClickListener(mOnClickListener);

        mButtonAries.setOnClickListener(mOnClickListener);
        mButtonLeo.setOnClickListener(mOnClickListener);
        mButtonSagittarius.setOnClickListener(mOnClickListener);

        mButtonCapricorn.setOnClickListener(mOnClickListener);
        mButtonTaurus.setOnClickListener(mOnClickListener);
        mButtonVirgo.setOnClickListener(mOnClickListener);

        mButtonCancer.setOnClickListener(mOnClickListener);
        mButtonScorpio.setOnClickListener(mOnClickListener);
        mButtonPisces.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
            intent.putExtra(INTENT_KEY, v.getId());
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anim_start_for_signs, R.anim.anim_finish_for_signs).toBundle();
            startActivity(intent, bndlanimation);

        }
    };
}
