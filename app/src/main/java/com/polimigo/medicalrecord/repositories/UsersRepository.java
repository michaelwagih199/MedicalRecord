package com.polimigo.medicalrecord.repositories;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.medicalrecord.helpers.SharedPrefrenceHelper;
import com.polimigo.medicalrecord.helpers.ToastMessage;
import com.polimigo.medicalrecord.models.Users;
import com.polimigo.medicalrecord.views.doctor_screen.DoctorScreen;
import com.polimigo.medicalrecord.views.login_screen.LoginScreen;
import com.polimigo.medicalrecord.views.patient.PatientHomeScreen;


public class UsersRepository {

    /* ContactsFirestoreManager object **/
    private static UsersRepository usersRepository;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();


    public static UsersRepository newInstance() {
        if (usersRepository == null) {
            usersRepository = new UsersRepository();
        }
        return usersRepository;
    }


    private UsersRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("users");
    }



    public void createDocument(Users contact, final Context context) {
        contactsCollectionReference
                .whereEqualTo("nationalId", contact.getNationalId())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult().getDocuments().isEmpty()) {
                        contactsCollectionReference.add(contact).addOnCompleteListener(command -> {
                        });
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage("user saved Succesfully");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        context.startActivity(new Intent(context.getApplicationContext(),LoginScreen.class));
                                        ((Activity) context).finish();

                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    } else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage("User Already Saved");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });


    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateContact(Users contact) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void isUser(final String userName, String password, final String userType, final Context context) {
        boolean isUser = false;
        contactsCollectionReference
                .whereEqualTo("userType", userType)
                .whereEqualTo("userName", userName)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult().isEmpty()) {
                        Intent i = new Intent(context.getApplicationContext(), LoginScreen.class);
                        context.startActivity(i);
                        ToastMessage.addMessage("check user name or password", context);
                        ((Activity) context).finish();
                    }
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.getData().isEmpty()) {
                                ToastMessage.addMessage("check user name or password", context);
                            } else {
                                if (userType.equals("patient")) {
                                    Intent i = new Intent(context.getApplicationContext(), PatientHomeScreen.class);
                                    sharedPrefrenceHelper.setUsername(context, userName);
                                    sharedPrefrenceHelper.setNationalId(context,document.getData().get("nationalId").toString());
                                    context.startActivity(i);
                                    ((Activity) context).finish();
                                }
                                if (userType.equals("doctor")) {
                                    Intent i = new Intent(context.getApplicationContext(), DoctorScreen.class);
                                    sharedPrefrenceHelper.setUsername(context, userName);
                                    context.startActivity(i);
                                    ((Activity) context).finish();
                                }

                            }
                        }
                    }
                });


    }


}
