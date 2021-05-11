package com.polimigo.medicalrecord.views.login_screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityLoginScreenBinding;
import com.polimigo.medicalrecord.viewmodels.LoginViewModel;
import com.polimigo.medicalrecord.views.AboutApplicationScreen;
import com.polimigo.medicalrecord.views.events.RegisterEvents;
import com.polimigo.medicalrecord.views.viewUtiliti.ViewDialog;

public class LoginScreen extends AppCompatActivity implements RegisterEvents {
    private static final String TAG = "LoginScreen";
    private static final int REQUEST_CODE = 1;

    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginScreenBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        activityMainBinding.setViewModel(new LoginViewModel(this, this));
        activityMainBinding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
        verifyPermissions();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onSignUp(View view) {
        ViewDialog alert = new ViewDialog();
        alert.showDialog(LoginScreen.this, "Chose Profile Type");
    }

    public void onAboutApplication(View view) {
        startActivity(new Intent(this, AboutApplicationScreen.class));
//        finish();
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
        llProgressBar.setVisibility(View.GONE);
    }


    private void verifyPermissions() {
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        String[] permissions = {
                Manifest.permission.VIBRATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[2]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[3]) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "ACCEPTED CAMERA");

        } else {
            ActivityCompat.requestPermissions(LoginScreen.this,
                    permissions,
                    REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPermissions();
    }

}