package com.vadimsleonovs.horoscope;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final static String USER_YEAR = "user_year";
    final static String USER_MONTH = "user_month";
    final static String USER_DAY = "user_day";
    final static String USER_STATUS = "user_status";
    final static String USER_SEX = "user_sex";
    final static String FIRST_TIME_CHECK = "first_time";
    //Need to store in SharedPreferences
    static int mYear;
    static int mMonth;
    static int mDay;
    static String mCheck = "checked";
    int mSexId;
    int mStatusId;
    private TextView mDateTextView;
    private RadioGroup mRadioSexGroup;
    private RadioButton mMaleButton;
    private RadioButton mFemaleButton;
    private RadioGroup mRadioStatusGroup;
    private RadioButton mInRelationshipButton;
    private RadioButton mSingleButton;
    private View mFragment;
    private String mDate;
    private Button mButton;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mDateTextView = (TextView) findViewById(R.id.date_view);
        mButton = (Button) findViewById(R.id.save_button);
        mFragment = findViewById(R.id.partner_container);
        mFragment.setVisibility(View.GONE);

        mRadioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        mMaleButton = (RadioButton) findViewById(R.id.male);
        mFemaleButton = (RadioButton) findViewById(R.id.female);

        mRadioStatusGroup = (RadioGroup) findViewById(R.id.user_status);
        mInRelationshipButton = (RadioButton) findViewById(R.id.in_relationship);
        mSingleButton = (RadioButton) findViewById(R.id.status_single);


        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        SettingsActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(R.color.mdtp_accent_color);
                dpd.setThemeDark(true);
                dpd.show(getFragmentManager(), "DatePickerDialog");

            }
        });


        mInRelationshipButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFragment.setVisibility(View.VISIBLE);
                } else {
                    mFragment.setVisibility(View.INVISIBLE);
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        mDate = "Твой День Рождения: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mDateTextView.setText(mDate);

    }

    public void onDataSetDefault(int year, int monthOfYear, int dayOfMonth) {
        mDate = "Твой День Рождения: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mDateTextView.setText(mDate);
    }

    public void saveUserInfo() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putInt(USER_DAY, mDay);
        editor.putInt(USER_MONTH, mMonth);
        editor.putInt(USER_YEAR, mYear);
        editor.putString("unknown_key", "key");

        mSexId = mRadioSexGroup.getCheckedRadioButtonId();
        mStatusId = mRadioStatusGroup.getCheckedRadioButtonId();
        editor.putInt(USER_SEX, mSexId);
        editor.putInt(USER_STATUS, mStatusId);
        editor.commit();
    }

    public void restoreUserInfo() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mYear = mSharedPreferences.getInt(USER_YEAR, mYear);
        mMonth = mSharedPreferences.getInt(USER_MONTH, mMonth);
        mDay = mSharedPreferences.getInt(USER_DAY, mDay);


        if ((mSharedPreferences.getInt(USER_SEX, mSexId)) == R.id.male) {
            mMaleButton.setChecked(true);
        } else if ((mSharedPreferences.getInt(USER_SEX, mSexId)) == R.id.female) {
            mFemaleButton.setChecked(true);
        } else {
            mMaleButton.setChecked(false);
            mFemaleButton.setChecked(false);
        }


        if (mSharedPreferences.getInt(USER_STATUS, mStatusId) == R.id.in_relationship) {
            mInRelationshipButton.setChecked(true);
        } else if ((mSharedPreferences.getInt(USER_STATUS, mStatusId)) == R.id.status_single) {
            mSingleButton.setChecked(true);
        } else {
            mInRelationshipButton.setChecked(false);
            mSingleButton.setChecked(false);
        }


        if (mYear != 0 && mMonth != 0 && mDay != 0) {
            onDataSetDefault(mYear, mMonth, mDay);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveUserInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreUserInfo();
    }
}
