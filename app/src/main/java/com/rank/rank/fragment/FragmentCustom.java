package com.rank.rank.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.rank.rank.DatePopUpActivity;
import com.rank.rank.R;
import com.rank.rank.databinding.FragmentCustomBinding;
import com.rank.rank.databinding.FragmentProjectBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class FragmentCustom extends Fragment {

    private FragmentCustomBinding binding;
    DecimalFormat df = new DecimalFormat("###,###.####");
    private int btnNumber=0;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(btnNumber);
        }
    };

    String formatResult;
    public FragmentCustom(){

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try{
//
//
//        if(requestCode == 1){
//                   // Toast.makeText(getContext(),data.getIntExtra("yyyy",0)+"",Toast.LENGTH_SHORT).show();
//                    binding.startDay.setText(data.getIntExtra("yyyy",0)+"."
//                    +data.getIntExtra("mm",0)+"."+data.getIntExtra("dd",0));
//                    binding.subConst2.setVisibility(View.VISIBLE);
//        }else if (requestCode == 2){
//          //  Toast.makeText(getContext(),data.getIntExtra("yyyy",0)+"",Toast.LENGTH_SHORT).show();
//            binding.end.setText(data.getIntExtra("yyyy",0)+"."
//                    +data.getIntExtra("mm",0)+"."+data.getIntExtra("dd",0));
//        }
//
//        }catch (NullPointerException ex){
//            Toast.makeText(getContext(), "값을 선택해주세요", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_custom,container,false);

        View view=binding.getRoot();


        binding.btnDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnDeveloper.setTextColor(Color.parseColor("#ffffff"));
                binding.btnDeveloper.setBackgroundResource(R.drawable.choice_btn);
                binding.btnDeveloper.setTypeface(null,Typeface.BOLD);

                binding.btnDesign.setTextColor(Color.parseColor("#333333"));
                binding.btnDesign.setBackgroundResource(R.drawable.btn_radius);
                binding.btnDesign.setTypeface(null,Typeface.NORMAL);

                binding.btnConsulting.setTextColor(Color.parseColor("#333333"));
                binding.btnConsulting.setBackgroundResource(R.drawable.btn_radius);
                binding.btnConsulting.setTypeface(null,Typeface.NORMAL);
                if(binding.subConst.getVisibility()==View.INVISIBLE) {
                    binding.subConst.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnDesign.setTextColor(Color.parseColor("#ffffff"));
                binding.btnDesign.setBackgroundResource(R.drawable.choice_btn);
                binding.btnDesign.setTypeface(null,Typeface.BOLD);

                binding.btnDeveloper.setTextColor(Color.parseColor("#333333"));
                binding.btnDeveloper.setBackgroundResource(R.drawable.btn_radius);
                binding.btnDeveloper.setTypeface(null,Typeface.NORMAL);

                binding.btnConsulting.setTextColor(Color.parseColor("#333333"));
                binding.btnConsulting.setBackgroundResource(R.drawable.btn_radius);
                binding.btnConsulting.setTypeface(null,Typeface.NORMAL);

                if(binding.subConst.getVisibility()==View.INVISIBLE) {
                    binding.subConst.setVisibility(View.VISIBLE);
                }

            }
        });

        binding.btnConsulting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnConsulting.setTextColor(Color.parseColor("#ffffff"));
                binding.btnConsulting.setBackgroundResource(R.drawable.choice_btn);
                binding.btnConsulting.setTypeface(null,Typeface.BOLD);

                binding.btnDesign.setTextColor(Color.parseColor("#333333"));
                binding.btnDesign.setBackgroundResource(R.drawable.btn_radius);
                binding.btnDesign.setTypeface(null,Typeface.NORMAL);

                binding.btnDeveloper.setTextColor(Color.parseColor("#333333"));
                binding.btnDeveloper.setBackgroundResource(R.drawable.btn_radius);
                binding.btnDeveloper.setTypeface(null,Typeface.NORMAL);
                if(binding.subConst.getVisibility()==View.INVISIBLE) {
                    binding.subConst.setVisibility(View.VISIBLE);
                }
            }
        });



        binding.startDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Date date = new Date(System.currentTimeMillis());
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
//                String today = simpleDateFormat.format(date);
//                String[] YMD = today.split("/");
//                Toast.makeText(getContext(),  today, Toast.LENGTH_SHORT).show();
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), listener, Integer.parseInt(YMD[0]), Integer.parseInt(YMD[1])-1,
//                        Integer.parseInt(YMD[2]));
//
//                datePickerDialog.show();
                btnNumber=0;

                new DatePickerDialog(getContext(), myDatePicker,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


//                Intent intent = new Intent(getContext(), DatePopUpActivity.class);
//                startActivityForResult(intent,1);

            }
        });
        binding.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),DatePopUpActivity.class);
//                startActivityForResult(intent,2);
                btnNumber=1;
                new DatePickerDialog(getContext(), myDatePicker,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });





        binding.priceEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    if(binding.resultSearch.getVisibility() == View.INVISIBLE) {
                        binding.resultSearch.setVisibility(View.VISIBLE);
                    }
                }
                if(!s.toString().equals(formatResult)){
                    try {

                        formatResult=df.format(Long.parseLong(s.toString().replaceAll(",", "")));
                        binding.priceEdt.setText(formatResult);
                        binding.priceEdt.setSelection(formatResult.length());

                    }catch (NumberFormatException ex){
                        if(s.length()>0) {
                            Toast.makeText(getContext(), "액수 범위를 초과했습니다.", Toast.LENGTH_SHORT).show();
                            formatResult = "";
                            binding.priceEdt.setText("");
                        }
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;


    }

    private void updateLabel(int btnNumber) {
        String myFormat = "yyyy.MM.dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        switch (btnNumber){
            case 0:
                binding.startDay.setText(sdf.format(myCalendar.getTime()));
                break;
            case 1:
                binding.end.setText(sdf.format(myCalendar.getTime()));
                break;
        }
        binding.subConst2.setVisibility(View.VISIBLE);


    }
//    public void btnClick(View view){
//        switch (view.getId()){
//            case R.id.btn_developer:
//                binding.btnDeveloper.setTextColor(Color.parseColor("#ffffff"));
//                binding.btnDeveloper.setBackgroundResource(R.drawable.choice_btn);
//                break;
//            case R.id.btn_design:
//                binding.btnDesign.setTextColor(Color.parseColor("#ffffff"));
//                binding.btnDesign.setBackgroundResource(R.drawable.choice_btn);
//                break;
//            case R.id.btn_consulting:
//                binding.btnConsulting.setTextColor(Color.parseColor("#ffffff"));
//                binding.btnConsulting.setBackgroundResource(R.drawable.choice_btn);
//                break;
//        }

//    }
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(getContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();


            }
        };
}
