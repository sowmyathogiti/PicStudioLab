package com.example.picstudiolab.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.picstudiolab.FrameSetup;
import com.example.picstudiolab.Home;
import com.example.picstudiolab.Invitation;
import com.example.picstudiolab.Payment;
import com.example.picstudiolab.R;
import com.example.picstudiolab.Wedding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button regularFrames = root.findViewById(R.id.framebtn);
        Button weddingFrames = root.findViewById(R.id.framebtn2);
        Button invitationFrames = root.findViewById(R.id.framebtn3);
        regularFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frame1Intent = new Intent(getActivity(), FrameSetup.class);
                startActivity(frame1Intent);
            }
        });

        weddingFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frame2Intent = new Intent(getActivity(), Wedding.class);
                startActivity(frame2Intent);
            }
        });

        invitationFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frame3Intent = new Intent(getActivity(), Invitation.class);
                startActivity(frame3Intent);
            }
        });
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

}