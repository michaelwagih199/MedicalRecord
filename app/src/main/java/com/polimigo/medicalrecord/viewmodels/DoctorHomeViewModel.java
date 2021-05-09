package com.polimigo.medicalrecord.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.medicalrecord.BR;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.helpers.StaticData;
import com.polimigo.medicalrecord.models.DoctorModel;
import com.polimigo.medicalrecord.repositories.DoctorRepository;
import com.polimigo.medicalrecord.views.code.PatientCode;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

import java.util.List;

public class DoctorHomeViewModel extends BaseObservable {

    private DoctorModel doctorModel;
    private Context context;
    private String btnTextCheck;
    private String successMessage = "Successful";
    private String errorMessage = "Please fill All Data";
    private RegisterEvents registerEvents;
    private DoctorRepository doctorRepository;

    @Bindable
    private String toastMessage = null;

    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public DoctorHomeViewModel(DoctorModel doctorModel,String btnTextCheck, Context context ,RegisterEvents registerEvents) {
        this.doctorModel = doctorModel;
        this.btnTextCheck = btnTextCheck;
        this.context = context;
        this.registerEvents = registerEvents;
        doctorRepository = DoctorRepository.newInstance();
    }

    public DoctorModel getDoctorModel() {
        return doctorModel;
    }

    public void setDoctorModel(DoctorModel doctorModel) {
        this.doctorModel = doctorModel;
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

    public List<String> getData() {
        return StaticData.getEgyptGovernorate();
    }

    public void saveData() {
        if (isInputDataValid()) {
            DoctorModel model = new DoctorModel();
            model.setDoctorGovernorate(doctorModel.getDoctorGovernorate());
            model.setDoctorAddress(doctorModel.getDoctorAddress());
            model.setDoctorPhone(doctorModel.getDoctorPhone());
            model.setDoctorSpecialization(doctorModel.getDoctorSpecialization());
            model.setDocumentId(doctorModel.getDocumentId());
            model.setUserName(sharedPrefrenceHelper.getUsername(context));
            if (btnTextCheck.equals("Save")) {
                registerEvents.onStartedL();
                doctorRepository.createDocument(model, context);
            } else if (btnTextCheck.equals("Edit")) {
                registerEvents.onStartedL();
                doctorRepository.updateContact(model, context);
            }
        } else
            setToastMessage(errorMessage);
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        doctorModel.setDoctorGovernorate(parent.getSelectedItem().toString());
    }


    public void generateDoctorCode(){
        Intent i = new Intent(context, PatientCode.class);
        i.putExtra("USER_ID",doctorModel.getDocumentId());
        context.startActivity(i);
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(doctorModel.getDoctorAddress())
                && !TextUtils.isEmpty(doctorModel.getDoctorPhone())
                && !TextUtils.isEmpty(doctorModel.getDoctorSpecialization())
                && !doctorModel.getDoctorGovernorate().equals("All governorates"))
            result = true;
        return result;
    }

}
