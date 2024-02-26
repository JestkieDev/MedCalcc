package com.example.medcalc.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.medcalc.R;
import com.example.medcalc.databinding.FragmentHomeBinding;
import com.example.medcalc.ui.CapFragment;
import com.example.medcalc.ui.DefFragment;
import com.example.medcalc.ui.ImtFragment;
import com.example.medcalc.ui.InfFragment;

public class HomeFragment extends Fragment {
    Button imtBTN;
    Button infBTN;
    Button defBTN;
    Button capBTN;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imtBTN = view.findViewById(R.id.imtBTN);
        infBTN = view.findViewById(R.id.infBTN);
        defBTN = view.findViewById(R.id.calBTN);
        capBTN = view.findViewById(R.id.capBTN);

        imtBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImtFragment imtFragment = new ImtFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,imtFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        infBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfFragment infFragment = new InfFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,infFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        defBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefFragment defFragment = new DefFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,defFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        capBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapFragment capFragment = new CapFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,capFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;


    }



}