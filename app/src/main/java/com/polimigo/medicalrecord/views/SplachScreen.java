package com.polimigo.medicalrecord.views;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.views.login_screen.LoginScreen;

public class SplachScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread timer =new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            Intent i = new Intent(SplachScreen.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            timer.start();
        }
        else{

        }
    }
}