package com.polimigo.medicalrecord.views.viewUtiliti;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.views.register_screen.RegisterScreen;


public class ViewDialog {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.my_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);

        text.setText(msg);

        Button btnOwner = (Button) dialog.findViewById(R.id.btnPatient);
        btnOwner.setOnClickListener(v -> {
            Intent mIntent = new Intent(activity, RegisterScreen.class);
            mIntent.putExtra("userType", "patient");
            activity.startActivity(mIntent);
            activity.finish();
            dialog.dismiss();
        });

        Button btnSeeker = (Button) dialog.findViewById(R.id.btnDoctor);
        btnSeeker.setOnClickListener(v -> {
            Intent mIntent = new Intent(activity, RegisterScreen.class);
            mIntent.putExtra("userType", "doctor");
            activity.startActivity(mIntent);
            activity.finish();
            dialog.dismiss();
        });
        dialog.show();
    }
}
