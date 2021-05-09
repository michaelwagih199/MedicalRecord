package com.polimigo.medicalrecord.models;

import com.google.firebase.firestore.DocumentId;

public class DoctorModel {

    @DocumentId
    private String documentId;
    private String doctorPhone;
    private String doctorGovernorate;
    private String doctorAddress;
    private String doctorSpecialization;
    private String userName;

    public DoctorModel(String documentId, String doctorPhone, String doctorGovernorate, String doctorAddress, String doctorSpecialization,String userName) {
        this.documentId = documentId;
        this.doctorPhone = doctorPhone;
        this.doctorGovernorate = doctorGovernorate;
        this.doctorAddress = doctorAddress;
        this.doctorSpecialization = doctorSpecialization;
        this.userName = userName;
    }

    public DoctorModel() {
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorGovernorate() {
        return doctorGovernorate;
    }

    public void setDoctorGovernorate(String doctorGovernorate) {
        this.doctorGovernorate = doctorGovernorate;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
