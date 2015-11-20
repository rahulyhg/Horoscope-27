package com.vadimsleonovs.horoscope;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by vadims.leonovs on 10/27/2015.
 */
public class FragmentSettings extends Fragment implements DatePickerDialog.OnDateSetListener {

    final static String PARTNER_YEAR = "partner_year";
    final static String PARTNER_MONTH = "partner_month";
    final static String PARTNER_DAY = "partner_day";
    static int mYear;
    static int mMonth;
    static int mDay;
    private TextView mDateTextView;
    private SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, null);

        mDateTextView = (TextView) v.findViewById(R.id.partner_date_view);



        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        FragmentSettings.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(R.color.mdtp_accent_color);
                dpd.setThemeDark(true);
                dpd.show(getFragmentManager(), "DatePickerDialog");
            }
        });
        return v;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        String mDate = "День Рождения твоего партнёра: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mDateTextView.setText(mDate);

    }

    public void onDataSetDefault(int year, int monthOfYear, int dayOfMonth) {
        String mDate = "День Рождения твоего партнёра: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mDateTextView.setText(mDate);
    }

    public void saveUserInfo() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(PARTNER_DAY, mDay);
        editor.putInt(PARTNER_MONTH, mMonth);
        editor.putInt(PARTNER_YEAR, mYear);
        editor.commit();
        Toast.makeText(getActivity(), "Saved Fragment", Toast.LENGTH_SHORT).show();

    }

    public void restoreUserInfo() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        mYear = mSharedPreferences.getInt(PARTNER_YEAR, mYear);
        mMonth = mSharedPreferences.getInt(PARTNER_MONTH, mMonth);
        mDay = mSharedPreferences.getInt(PARTNER_DAY, mDay);

        if (mYear != 0 && mMonth != 0 && mDay != 0) {
            onDataSetDefault(mYear, mMonth, mDay);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        restoreUserInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveUserInfo();
    }
}
