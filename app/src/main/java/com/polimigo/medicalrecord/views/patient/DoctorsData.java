package com.polimigo.medicalrecord.views.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityDoctorScreenBindingImpl;
import com.polimigo.medicalrecord.databinding.DoctorItemRowBinding;
import com.polimigo.medicalrecord.repositories.DoctorRepository;

public class DoctorsData extends AppCompatActivity {

    private DoctorItemRowBinding binding;
    private DoctorRepository doctorRepository;
    String documentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.doctor_item_row);
        doctorRepository = DoctorRepository.newInstance();
        documentId = getIntent().getStringExtra("DocumentId");
        populateData();
    }

    private void populateData() {
        doctorRepository.findByDocmentId(documentId, binding, this);
    }

}