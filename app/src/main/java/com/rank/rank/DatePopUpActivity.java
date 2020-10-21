package com.rank.rank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.rank.rank.databinding.ActivityPopupDateBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePopUpActivity extends AppCompatActivity {
    private ActivityPopupDateBinding binding;

    private int yyyy,mm,dd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_popup_date);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
        String today = simpleDateFormat.format(date);
        String[] YMD = today.split("/");

//        dateModel = new DateModel(Integer.parseInt(YMD[0]),Integer.parseInt(YMD[1]),Integer.parseInt(YMD[2]));
//
//        dateModel = ViewModelProviders.of(this).get(DateModel.class);

        binding.setDatepicker(this);
        yyyy=Integer.parseInt(YMD[0]);
        mm = Integer.parseInt(YMD[1]);
        dd=Integer.parseInt(YMD[2]);

        binding.dateSpinner.init(yyyy,mm,dd,dateChangedListener);


        binding.resultTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ymd데이터",yyyy+","+mm+","+dd);
                Intent intent = new Intent();
                intent.putExtra("yyyy",yyyy);

                intent.putExtra("mm", mm);
                intent.putExtra("dd",dd);
                setResult(RESULT_OK, intent);

                finish();
            }
        });





    }
    DatePicker.OnDateChangedListener  dateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.dateText.setText(
                    year + "년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일"
            );
            yyyy=year;
            mm=monthOfYear;
            dd=dayOfMonth;
        }
    };
}
