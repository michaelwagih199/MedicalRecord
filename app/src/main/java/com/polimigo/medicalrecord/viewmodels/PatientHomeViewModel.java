package com.polimigo.medicalrecord.viewmodels;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.medicalrecord.BR;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.models.PatientModel;
import com.polimigo.medicalrecord.repositories.PatientRepository;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

public class PatientHomeViewModel extends BaseObservable {
    private PatientModel patientModel;
    private Context context;
    private String userName;
    private RegisterEvents registerEvents;
    private PatientRepository patientRepository;
    private String btnTextCheck;
    private String successMessage = "Successful";
    private String errorMessage = "Please fill All Data";
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    @Bindable
    private String toastMessage = null;

    public PatientHomeViewModel(PatientModel patientModel, Context context, String userName, String btnTextCheck, RegisterEvents registerEvents) {
        this.patientModel = patientModel;
        this.context = context;
        this.userName = userName;
        this.btnTextCheck = btnTextCheck;
        this.registerEvents = registerEvents;
        patientRepository = PatientRepository.newInstance();
    }

    public PatientModel getPatientModel() {
        return patientModel;
    }

    public void setPatientModel(PatientModel patientModel) {
        this.patientModel = patientModel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBtnTextCheck() {
        return btnTextCheck;
    }

    public void setBtnTextCheck(String btnTextCheck) {
        this.btnTextCheck = btnTextCheck;
    }


    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void saveData() {
        if (isInputDataValid()) {
            PatientModel model = new PatientModel();
            model.setPATIENT_ID(patientModel.getPATIENT_ID());
            model.setDIABETES(patientModel.getDIABETES());
            model.setHEART_STATUE(patientModel.getHEART_STATUE());
            model.setNOTES(patientModel.getNOTES());
            model.setDocumentId(patientModel.getDocumentId());
            model.setUsername(userName);
            model.setPRESSURE(patientModel.getPRESSURE());
            model.setSENSITIVITY(patientModel.getSENSITIVITY());
            model.setSURGERY(patientModel.getSURGERY());
            if (btnTextCheck.equals("Save")) {
                registerEvents.onStartedL();
                patientRepository.createDocument(model, context);
            } else if (btnTextCheck.equals("Edit")) {
                registerEvents.onStartedL();
                patientRepository.updateContact(model, context);
            }
        } else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(patientModel.getDIABETES())
                && !TextUtils.isEmpty(patientModel.getHEART_STATUE())
                && !TextUtils.isEmpty(patientModel.getPRESSURE())
                && !TextUtils.isEmpty(patientModel.getSENSITIVITY())
                && !TextUtils.isEmpty(patientModel.getSURGERY()))
            result = true;
        return result;
    }

}
