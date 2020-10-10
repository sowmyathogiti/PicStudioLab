package com.example.picstudiolab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class dashboardFragment extends Fragment {
    public dashboardFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        Button framebtn = (Button) view.findViewById(R.id.framebtn);
        framebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frame1Int = new Intent(getActivity(),Wedding.class);
                frame1Int.putExtra("some","some data");
                startActivity(frame1Int);
            }
        });
        return view;
    }
}
