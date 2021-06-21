package com.polimigo.medicalrecord.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.medicalrecord.databinding.ActivityDoctorProfileBindingImpl;
import com.polimigo.medicalrecord.databinding.ActivityDoctorScreenBindingImpl;
import com.polimigo.medicalrecord.databinding.DoctorItemRowBinding;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.helpers.ToastMessage;
import com.polimigo.medicalrecord.models.DoctorModel;
import com.polimigo.medicalrecord.viewmodels.DoctorHomeViewModel;
import com.polimigo.medicalrecord.views.adabters.DoctorRecycleViewAdabter;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorScreen;
import com.polimigo.medicalrecord.views.events.RegisterEvents;
import com.polimigo.medicalrecord.views.patient.DoctorsData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class DoctorRepository {

    private static DoctorRepository doctorRepository;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static DoctorRepository newInstance() {
        if (doctorRepository == null) {
            doctorRepository = new DoctorRepository();
        }
        return doctorRepository;
    }

    private DoctorRepository() {
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
                DoctorModel doctorModel = new DoctorModel("", "", "", "", "", "");
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

    public void getProfile(@NotNull String userName, ActivityDoctorScreenBindingImpl binding, RegisterEvents registerEvents, Context context) {
        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                DoctorModel doctorModel = new DoctorModel("", "", "", "", "", "");
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


    //get user of company
    public void getAllDoctors(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<DoctorModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    DoctorModel taskItem = document.toObject(DoctorModel.class);
                    list.add(taskItem);
                    DoctorRecycleViewAdabter nurslyRecyclerViewAdapter = new DoctorRecycleViewAdabter(list, mContext);
                    recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void findByGovernorate(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer, @NotNull String governorate) {
        ArrayList<DoctorModel> list = new ArrayList<>();
        contactsCollectionReference
                .whereEqualTo("doctorGovernorate", governorate)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                DoctorRecycleViewAdabter nurslyRecyclerViewAdapter = new DoctorRecycleViewAdabter(list, mContext);
                recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    DoctorModel taskItem = document.toObject(DoctorModel.class);
                    list.add(taskItem);
                    DoctorRecycleViewAdabter nurslyRecyclerViewAdapter = new DoctorRecycleViewAdabter(list, mContext);
                    recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                }
            }
        });
    }


    public void findByDocmentId(String documentId, DoctorItemRowBinding binding, Context context) {
        contactsCollectionReference.document(documentId)
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DoctorModel taskItem = task.getResult().toObject(DoctorModel.class);
                binding.setModel(taskItem);

              Log.e("succee",""+task.getResult().getData());
            }
        });
    }
}
