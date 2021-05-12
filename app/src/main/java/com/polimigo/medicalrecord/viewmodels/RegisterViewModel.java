package com.polimigo.medicalrecord.viewmodels;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.medicalrecord.BR;
import com.polimigo.medicalrecord.models.Users;
import com.polimigo.medicalrecord.repositories.UsersRepository;
import com.polimigo.medicalrecord.views.events.RegisterEvents;


public class RegisterViewModel extends BaseObservable {

    private Users users;
    @Bindable
    private String toastMessage = null;
    RegisterEvents registerEvents;
    private String successMessage = "Login was successful";
    private String errorMessage = "Please fill All data";
    private UsersRepository usersRepository;
    private Context context;

    public RegisterViewModel(RegisterEvents registerViewModel, String type, Context context) {
        users = new Users("", "", "", "", type, "");
        this.registerEvents = registerViewModel;
        usersRepository = UsersRepository.newInstance();
        this.context = context;
    }

    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public Users getUsers() {
        return users;
    }


    @Bindable
    public void setUsers(Users users) {
        this.users = users;
    }

    public void onRegisterClicked() {
        if (isInputDataValid()) {
            Users contact = new Users();
            contact.setFirstNameString(users.getFirstNameString());
            contact.setLastNameString(users.getLastNameString());
            contact.setUserName(users.getUserName());
            contact.setPassword(users.getPassword());
            contact.setUserType(users.getUserType());
            contact.setNationalId(users.getNationalId());
            usersRepository.createDocument(contact, context);
        } else {
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(users.getFirstNameString())
                && !TextUtils.isEmpty(users.getLastNameString())
                && !TextUtils.isEmpty(users.getPassword())
                && !TextUtils.isEmpty(users.getPassword())
                && users.getNationalId().length()==14)
            result = true;
        return result;
    }

}

