package com.polimigo.medicalrecord.views.doctor_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityDoctorScreenBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.repositories.DoctorRepository;
import com.polimigo.medicalrecord.views.code.SimpleScanner;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

public class DoctorScreen extends AppCompatActivity implements RegisterEvents {

    private View llProgressBar;
    private DoctorRepository doctorRepository;
    private SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    private ActivityDoctorScreenBindingImpl binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_screen);
        llProgressBar = findViewById(R.id.llProgressBar);
        doctorRepository = DoctorRepository.newInstance();
        populateData();
    }

    private void populateData() {
        doctorRepository.getProfile(sharedPrefrenceHelper.getUsername(this), binding, this, this);
    }

    public void editDoctorProfileClick(View view) {
        startActivity(new Intent(this,DoctorProfile.class));
    }

    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailerL() {

    }

    public void ScanPatientCode(View view) {
        Intent intent = new Intent(getBaseContext(), SimpleScanner.class);
        intent.putExtra("SCAN_TYPE", "PATIENT");
        startActivity(intent);
    }
}