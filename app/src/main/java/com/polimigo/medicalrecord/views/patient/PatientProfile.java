package com.polimigo.medicalrecord.views.patient;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityPatientProfileBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.repositories.PatientRepository;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

public class PatientProfile extends AppCompatActivity implements RegisterEvents {

    private View llProgressBar;
    private PatientRepository patientRepository;
    private SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    private ActivityPatientProfileBindingImpl binding;
    private String PATIENT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_profile);
        llProgressBar = findViewById(R.id.llProgressBar);
        patientRepository = PatientRepository.newInstance();
        PATIENT_ID = getIntent().getStringExtra("PATIENT_ID");
        populateData();
    }

    private void populateData() {
        patientRepository.editeProfile(PATIENT_ID, binding, this, this);
    }

    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {

    }

    @Override
    public void onFailerL() {

    }
}