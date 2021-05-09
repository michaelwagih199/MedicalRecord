package com.polimigo.medicalrecord.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.polimigo.medicalrecord.databinding.ActivityPatientHomeBindingImpl;
import com.polimigo.medicalrecord.databinding.ActivityPatientProfileBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.helpers.ToastMessage;
import com.polimigo.medicalrecord.models.PatientModel;
import com.polimigo.medicalrecord.viewmodels.PatientHomeViewModel;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorScreen;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

import org.jetbrains.annotations.NotNull;

public class PatientRepository {
    private static PatientRepository patientRepository;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static PatientRepository newInstance(){
        if (patientRepository ==null){
            patientRepository = new PatientRepository();
        }
        return patientRepository;
    }

    private PatientRepository(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("patients");
    }

    public void createDocument(PatientModel contact, final Context context) {
        contactsCollectionReference.add(contact).addOnCompleteListener(command -> {
            if (command.isSuccessful()) {
                ToastMessage.addMessage("Data Saved", context);
                Intent i = new Intent(context.getApplicationContext(), DoctorScreen.class);
                context.startActivity(i);
                ((Activity) context).finish();
            } else
                ToastMessage.addMessage("Saved Faild", context);
        });
    }


    public void updateContact(PatientModel contact, Context context) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
        Intent i = new Intent(context.getApplicationContext(), DoctorScreen.class);
        context.startActivity(i);
        ((Activity) context).finish();
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }


    public void getProfile(@NotNull String patient_ID, ActivityPatientHomeBindingImpl binding, Context context, RegisterEvents registerEvents) {
        contactsCollectionReference
                .whereEqualTo("patient_ID", patient_ID)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                PatientModel patientModel = new PatientModel("",sharedPrefrenceHelper.getNationalId(context),"", "", "", "", "", "",sharedPrefrenceHelper.getUsername(context));
                PatientHomeViewModel patientHomeViewModel = new PatientHomeViewModel(patientModel, context,sharedPrefrenceHelper.getUsername(context),"",registerEvents);
                binding.setViewModel(patientHomeViewModel);
                binding.executePendingBindings();
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    PatientModel taskItem = document.toObject(PatientModel.class);
                    taskItem.setPATIENT_ID(sharedPrefrenceHelper.getNationalId(context));
                    PatientHomeViewModel ownerProfileViewModel = new PatientHomeViewModel(taskItem, context,sharedPrefrenceHelper.getUsername(context),"",registerEvents);
                    binding.setViewModel(ownerProfileViewModel);
                    binding.executePendingBindings();
                }
            }
        });
    }

    public void editeProfile(String PATIENT_ID, ActivityPatientProfileBindingImpl binding, RegisterEvents registerEvents, Context context) {
        contactsCollectionReference
                .whereEqualTo("patient_ID", PATIENT_ID)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                PatientModel patientModel = new PatientModel("",PATIENT_ID,"", "", "", "", "", "","");
                PatientHomeViewModel patientHomeViewModel = new PatientHomeViewModel(patientModel, context,PATIENT_ID,"Save",registerEvents);
                binding.setViewModel(patientHomeViewModel);
                binding.executePendingBindings();
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    PatientModel taskItem = document.toObject(PatientModel.class);
                    PatientHomeViewModel patientHomeViewModel = new PatientHomeViewModel(taskItem, context,PATIENT_ID,"Edit",registerEvents);
                    binding.setViewModel(patientHomeViewModel);
                    binding.executePendingBindings();
                }
            }
        });

    }

}
