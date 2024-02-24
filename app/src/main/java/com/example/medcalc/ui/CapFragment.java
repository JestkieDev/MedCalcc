package com.example.medcalc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medcalc.R;
import com.example.medcalc.ui.home.HomeFragment;

public class CapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cap, container, false);
        EditText capET = view.findViewById(R.id.capET);
        EditText mcapET = view.findViewById(R.id.mcapET);
        Button okcapBTN = view.findViewById(R.id.okcapBTN);
        TextView rescapTV = view.findViewById(R.id.rescapTV);
        Button backBTN = view.findViewById(R.id.backcapBTN);

        okcapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cap = Integer.parseInt(capET.getText().toString());
                int mcap = Integer.parseInt(mcapET.getText().toString());
                int res = cap * 20/ mcap;
                rescapTV.setText(String.valueOf(res) + " капель в мин");

            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }
}