package com.polimigo.medicalrecord.views.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityPatientHomeBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.repositories.PatientRepository;
import com.polimigo.medicalrecord.views.code.PatientCode;
import com.polimigo.medicalrecord.views.code.SimpleScanner;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

public class PatientHomeScreen extends AppCompatActivity implements RegisterEvents {

    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    private  ActivityPatientHomeBindingImpl binding;
    private PatientRepository patientRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientRepository = PatientRepository.newInstance();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_patient_home);
        populateData();
    }

    private void populateData() {
        patientRepository.getProfile(sharedPrefrenceHelper.getNationalId(this), binding,this,this);
    }

    public void patientCodeClick(View view) {
        Intent i = new Intent(this, PatientCode.class);
        i.putExtra("USER_ID", sharedPrefrenceHelper.getNationalId(this));
        startActivity(i);
    }

    public void onScanDoctor(View view) {
        Intent intent = new Intent(getBaseContext(), SimpleScanner.class);
        intent.putExtra("SCAN_TYPE", "DOCTOR");
        startActivity(intent);
    }

    public void nearestDoctor(View view) {
        startActivity(new Intent(this, NearestDoctor.class));
    }

    @Override
    public void onStartedL() {

    }

    @Override
    public void onSuccessL() {

    }

    @Override
    public void onFailerL() {

    }
}