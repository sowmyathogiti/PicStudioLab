package com.example.picstudiolab.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.picstudiolab.MainActivity;
import com.example.picstudiolab.R;
import com.example.picstudiolab.UpdateProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationsFragment extends Fragment {

    FirebaseAuth mAuth1; //= FirebaseAuth.getInstance();
    FirebaseFirestore db1;// = FirebaseFirestore.getInstance();
    private NotificationsViewModel notificationsViewModel;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth1 = FirebaseAuth.getInstance();
        db1 = FirebaseFirestore.getInstance();
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ImageView logout = root.findViewById(R.id.logout);
        final TextView name = root.findViewById(R.id.name);
        final TextView mail = root.findViewById(R.id.mail);
        final TextView gender = root.findViewById(R.id.gender);
        final TextView agee= root.findViewById(R.id.agee);
        final TextView PrfAddressValue = root.findViewById(R.id.PrfAddressValue);
        final Button number = root.findViewById(R.id.number);
        final Button editProfile = root.findViewById(R.id.EditBtn);

        DocumentReference documentReference = db1.collection("customers").document(mAuth1.getCurrentUser().getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()){
                        Log.d("exist", "DocumentSnapshot data: " + documentSnapshot.getData());

                        name.setText((CharSequence) documentSnapshot.get("Name"));
                        mail.setText((CharSequence) documentSnapshot.get("EmailID"));
                        gender.setText((CharSequence) documentSnapshot.get("gender"));
                        agee.setText((CharSequence) documentSnapshot.get("Age"));
                        number.setText((CharSequence) documentSnapshot.get("Phone"));
                        PrfAddressValue.setText((CharSequence) documentSnapshot.get("address"));
                    }
                    else{
                        Log.d("not exist", "DocumentSnapshot data: " + documentSnapshot.getData());
                    }
                }
                else{
                    Log.d("data", "DocumentSnapshot data: " + task.getException());
                }
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);

//        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateProfile();
            }
        });

        return root;


    }

    private void getUpdateProfile() {
        Intent updProfileInt = new Intent(getContext(),UpdateProfile.class);
        startActivity(updProfileInt);
    }
}