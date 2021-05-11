package com.polimigo.medicalrecord.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceHelper {

    private String PREF_NAME = "prefs";
    private SharedPreferences getPrefs(Context context)  {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUsername(Context context , String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("userName", input);
        editor.commit();
    }

    public void setNationalId(Context context , String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("nationalId", input);
        editor.commit();
    }

    public String getNationalId(Context context){
        return getPrefs(context).getString("nationalId", "defulat");
    }


    public void saveLocationData(Context context, String Latitude,String Longitude ) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("Latitude", Latitude);
        editor.putString("Longitude", Longitude);
        editor.commit();
    }

    public String getUsername(Context context) {
        return getPrefs(context).getString("userName", "admin");
    }


    public String getLatitude(Context context) {
        return getPrefs(context).getString("Latitude", "-");
    }

    public String getLongitude(Context context) {
        return getPrefs(context).getString("Longitude", "-");
    }

}


