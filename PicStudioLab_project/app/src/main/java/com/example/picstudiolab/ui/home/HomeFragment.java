package com.example.picstudiolab.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.picstudiolab.BrowsePics;
import com.example.picstudiolab.Map;
import com.example.picstudiolab.R;
import com.example.picstudiolab.Wedding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {


    FirebaseAuth mAuth1; //= FirebaseAuth.getInstance();
    FirebaseFirestore db1;
    private HomeViewModel homeViewModel;
    TextView initialmsg;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth1 = FirebaseAuth.getInstance();
        db1 = FirebaseFirestore.getInstance();

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        Button locationbtn = root.findViewById(R.id.location);

//        locationbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cartInt = new Intent(getActivity(), Map.class);
//                startActivity(cartInt);
//            }
//        });
        initialmsg = root.findViewById(R.id.initialmsg);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        DocumentReference documentReference = db1.collection("customers").document(mAuth1.getCurrentUser().getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()){
                        Log.d("exist", "DocumentSnapshot data: " + documentSnapshot.getData());

                        initialmsg.setText("Hello "+(CharSequence) documentSnapshot.get("Name")+" welcome home");
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
        return root;
    }
}