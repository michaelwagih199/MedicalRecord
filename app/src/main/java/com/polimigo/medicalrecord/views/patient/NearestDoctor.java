package com.polimigo.medicalrecord.views.patient;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityNearestDoctorBinding;
import com.polimigo.medicalrecord.helpers.StaticData;
import com.polimigo.medicalrecord.repositories.DoctorRepository;

public class NearestDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityNearestDoctorBinding binding;
    private DoctorRepository doctorRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nearest_doctor);

        doctorRepository = DoctorRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nearest_doctor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.seekerData.setLayoutManager(linearLayoutManager);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerFilterData);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, StaticData.getEgyptGovernorate());
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        populateData();
    }

    private void populateData() {
        doctorRepository.getAllDoctors(this, binding.seekerData);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("All governorates"))
            doctorRepository.getAllDoctors(this, binding.seekerData);
        else
            doctorRepository.findByGovernorate(this, binding.seekerData, item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}