package com.polimigo.medicalrecord.viewmodels;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.medicalrecord.BR;
import com.polimigo.medicalrecord.models.Users;
import com.polimigo.medicalrecord.repositories.UsersRepository;
import com.polimigo.medicalrecord.views.events.RegisterEvents;


public class LoginViewModel extends BaseObservable {

    private Users users;
    private String successMessage = "Login was successful";
    private String errorMessage = "Login Failed";
    private UsersRepository usersRepository;
    RegisterEvents registerEvents;
    Context context;


    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel(RegisterEvents registerViewModel, Context context) {
        users = new Users("", "", "", "", "","");
        this.registerEvents = registerViewModel;
        this.context = context;
        usersRepository = UsersRepository.newInstance();
    }

    @Bindable
    public Users getUsers() {
        return users;
    }


    @Bindable
    public void setUsers(Users users) {
        this.users = users;
    }

    public void setDoctorUserType(){
        users.setUserType("doctor");
    }

    public void setPatientUserType(){
        users.setUserType("patient");
    }

    public void onLoginClicked() {
        if (isInputDataValid()) {
            registerEvents.onStartedL();
            usersRepository.isUser(
                    users.getUserName(),
                    users.getPassword(),
                    users.getUserType(),
                    context);
        } else {
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(users.getUserName()) &&
                !TextUtils.isEmpty(users.getPassword()) &&
                !users.getUserType().isEmpty();
    }

}