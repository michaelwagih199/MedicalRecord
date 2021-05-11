package com.polimigo.medicalrecord.viewmodels;

import android.content.Context;

import com.polimigo.medicalrecord.helpers.StaticData;

import java.util.List;

public class NearstDoctors {
    Context context;

    public List<String> getData() {
        return StaticData.getEgyptGovernorate();
    }

    public NearstDoctors(Context context) {
        this.context = context;
    }
}
