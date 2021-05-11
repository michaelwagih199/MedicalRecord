package com.polimigo.medicalrecord.views;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.Toast;
import com.polimigo.medicalrecord.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}