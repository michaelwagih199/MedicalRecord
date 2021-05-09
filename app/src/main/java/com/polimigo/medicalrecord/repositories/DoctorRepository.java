package com.polimigo.medicalrecord.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.polimigo.medicalrecord.databinding.ActivityDoctorProfileBindingImpl;
import com.polimigo.medicalrecord.databinding.ActivityDoctorScreenBindingImpl;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.helpers.ToastMessage;
import com.polimigo.medicalrecord.models.DoctorModel;
import com.polimigo.medicalrecord.viewmodels.DoctorHomeViewModel;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorScreen;
import com.polimigo.medicalrecord.views.events.RegisterEvents;

import org.jetbrains.annotations.NotNull;

public class DoctorRepository {
    private static DoctorRepository doctorRepository;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static DoctorRepository newInstance(){
        if (doctorRepository ==null){
            doctorRepository = new DoctorRepository();
        }
        return doctorRepository;
    }

    private DoctorRepository(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("doctors");
    }

    public void createDocument(DoctorModel contact, final Context context) {
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

    public void updateContact(DoctorModel contact, Context context) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
        Intent i = new Intent(context.getApplicationContext(), DoctorScreen.class);
        context.startActivity(i);
        ((Activity) context).finish();
    }


    public void editeProfile(String username, ActivityDoctorProfileBindingImpl binding, RegisterEvents registerEvents, Context context) {
        contactsCollectionReference
                .whereEqualTo("userName", username)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                DoctorModel doctorModel = new DoctorModel("", "", "", "", "","");
                DoctorHomeViewModel doctorHomeViewModel = new DoctorHomeViewModel(doctorModel, "Save", context, registerEvents);
                binding.setViewModel(doctorHomeViewModel);
                binding.executePendingBindings();
            }

            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    DoctorModel taskItem = document.toObject(DoctorModel.class);
                    DoctorHomeViewModel nurslyProfileViewModel = new DoctorHomeViewModel(taskItem, "Edit", context, registerEvents);
                    binding.setViewModel(nurslyProfileViewModel);
                    binding.executePendingBindings();
                }
            }
        });

    }

    public void getProfile(@NotNull String userName, ActivityDoctorScreenBindingImpl binding, RegisterEvents registerEvents,Context context ) {
        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                DoctorModel doctorModel = new DoctorModel("", "", "", "", "","");
                DoctorHomeViewModel doctorHomeViewModel = new DoctorHomeViewModel(doctorModel, "Save", context, registerEvents);
                binding.setViewModel(doctorHomeViewModel);
                binding.executePendingBindings();
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    DoctorModel taskItem = document.toObject(DoctorModel.class);
                    DoctorHomeViewModel nurslyProfileViewModel = new DoctorHomeViewModel(taskItem, "Edit", context, registerEvents);
                    binding.setViewModel(nurslyProfileViewModel);
                    binding.executePendingBindings();
                }
            }
        });
    }

}
