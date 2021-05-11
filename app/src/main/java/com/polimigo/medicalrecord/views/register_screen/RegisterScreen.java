package com.polimigo.medicalrecord.views.register_screen;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.polimigo.babydaycare.view.viewUtiliti.AwsemdialogIm;
import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.ActivityRegisterScreenBinding;
import com.polimigo.medicalrecord.viewmodels.RegisterViewModel;
import com.polimigo.medicalrecord.views.events.RegisterEvents;
import com.polimigo.medicalrecord.views.login_screen.LoginScreen;

public class RegisterScreen extends AppCompatActivity  implements RegisterEvents {
    View llProgressBar;
    private ActivityRegisterScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this, R.layout.activity_register_screen);
        String userType = getIntent().getExtras().getString("userType");
        RegisterViewModel registerViewModel = new RegisterViewModel(this,userType,this);
        binding.setRegisterModel(registerViewModel);
        binding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
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
                new Intent(this, LoginScreen.class),
                "Sucess", "body");
    }

    @Override
    public void onFailerL() {

    }
}