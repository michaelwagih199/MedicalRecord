package com.polimigo.medicalrecord.views.doctor_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.polimigo.babydaycare.view.viewUtiliti.AwsemdialogIm;
import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityDoctorProfileBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.repositories.DoctorRepository;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

public class DoctorProfile extends AppCompatActivity implements RegisterEvents {

    private View llProgressBar;
    private DoctorRepository doctorRepository;
    private SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    private ActivityDoctorProfileBindingImpl binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_profile);
        llProgressBar = findViewById(R.id.llProgressBar);
        doctorRepository = DoctorRepository.newInstance();
        populateData();
    }

    private void populateData() {
        doctorRepository.editeProfile(sharedPrefrenceHelper.getUsername(this), binding, this, this);
    }

    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
        AwsemdialogIm awsemdialogIm = new AwsemdialogIm();
        awsemdialogIm.sucssDialog(this,
                new Intent(this, DoctorScreen.class),
                "Sucess", "body");
    }

    @Override
    public void onFailerL() {

    }
}