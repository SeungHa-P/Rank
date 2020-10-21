package com.rank.rank;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class DateModel extends ViewModel {

    public DateModel(Integer YYYY,Integer MM, Integer DD){
        this.YYYY.setValue(YYYY);
        this.MM.setValue(MM);
        this.DD.setValue(DD);

    }

    MutableLiveData<Integer> YYYY = new MutableLiveData<>();
    MutableLiveData<Integer> MM = new MutableLiveData<>();
    MutableLiveData<Integer> DD = new MutableLiveData<>();






}
