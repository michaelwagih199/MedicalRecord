package com.polimigo.medicalrecord.models;

import com.google.firebase.firestore.DocumentId;

public class PatientModel {
    @DocumentId
    private String documentId;
    private String PATIENT_ID,DIABETES,PRESSURE,SURGERY,NOTES, SENSITIVITY, HEART_STATUE;
    private String username;

    public PatientModel() {
    }

    public PatientModel(String documentId, String PATIENT_ID, String DIABETES, String PRESSURE, String SURGERY, String NOTES, String SENSITIVITY, String HEART_STATUE,String username) {
        this.documentId = documentId;
        this.PATIENT_ID = PATIENT_ID;
        this.DIABETES = DIABETES;
        this.PRESSURE = PRESSURE;
        this.SURGERY = SURGERY;
        this.NOTES = NOTES;
        this.SENSITIVITY = SENSITIVITY;
        this.HEART_STATUE = HEART_STATUE;
        this.username = username;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPATIENT_ID() {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(String PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getDIABETES() {
        return DIABETES;
    }

    public void setDIABETES(String DIABETES) {
        this.DIABETES = DIABETES;
    }

    public String getPRESSURE() {
        return PRESSURE;
    }

    public void setPRESSURE(String PRESSURE) {
        this.PRESSURE = PRESSURE;
    }

    public String getSURGERY() {
        return SURGERY;
    }

    public void setSURGERY(String SURGERY) {
        this.SURGERY = SURGERY;
    }

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }

    public String getSENSITIVITY() {
        return SENSITIVITY;
    }

    public void setSENSITIVITY(String SENSITIVITY) {
        this.SENSITIVITY = SENSITIVITY;
    }

    public String getHEART_STATUE() {
        return HEART_STATUE;
    }

    public void setHEART_STATUE(String HEART_STATUE) {
        this.HEART_STATUE = HEART_STATUE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "documentId='" + documentId + '\'' +
                ", PATIENT_ID='" + PATIENT_ID + '\'' +
                ", DIABETES='" + DIABETES + '\'' +
                ", PRESSURE='" + PRESSURE + '\'' +
                ", SURGERY='" + SURGERY + '\'' +
                ", NOTES='" + NOTES + '\'' +
                ", SENSITIVITY='" + SENSITIVITY + '\'' +
                ", HEART_STATUE='" + HEART_STATUE + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
