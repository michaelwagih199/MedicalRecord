package com.polimigo.medicalrecord.views.code;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorProfile;
import com.polimigo.medicalrecord.views.patient.DoctorsData;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DoctorScanner extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String ScannerType;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);// Set the scanner view as the content view
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
        Intent intent = new Intent(getBaseContext(), DoctorsData.class);
        intent.putExtra("DocumentId", rawResult.getText());
        startActivity(intent);
        mScannerView.resumeCameraPreview(this);
    }

}