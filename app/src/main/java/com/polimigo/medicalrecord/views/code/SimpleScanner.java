package com.polimigo.medicalrecord.views.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorProfile;
import com.polimigo.medicalrecord.views.patient.PatientProfile;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScanner extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private String ScannerType;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);// Set the scanner view as the content view
        ScannerType = getIntent().getStringExtra("SCAN_TYPE");
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        if (ScannerType == "DOCTOR") {
            Intent intent = new Intent(getBaseContext(), DoctorProfile.class);
            intent.putExtra("PATIENT_ID", rawResult.getText());
            startActivity(intent);
            mScannerView.resumeCameraPreview(this);
        } else {
            Intent intent = new Intent(getBaseContext(), PatientProfile.class);
            intent.putExtra("PATIENT_ID", rawResult.getText());
            startActivity(intent);
            mScannerView.resumeCameraPreview(this);
        }

    }

}