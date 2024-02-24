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

import java.text.DecimalFormat;

public class InfFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inf, container, false);
        EditText mgET = view.findViewById(R.id.mgET);
        EditText mlET = view.findViewById(R.id.mlET);
        EditText minfET = view.findViewById(R.id.minfET);
        EditText dozET = view.findViewById(R.id.dozET);
        Button okinfBTN = view.findViewById(R.id.okinfBTN);
        TextView resinfTV = view.findViewById(R.id.resinfTV);
        Button backBTN = view.findViewById(R.id.backinfBTN);

        okinfBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mg = Integer.parseInt(mgET.getText().toString());
                int ml = Integer.parseInt(mlET.getText().toString());
                int minf = Integer.parseInt(minfET.getText().toString());
                int doz = Integer.parseInt(dozET.getText().toString());
                double res = (double) minf*doz / (mg* (1000.0/ml))*60;
                DecimalFormat df = new DecimalFormat("###.##");
                resinfTV.setText(df.format(res) + " мл/ч");
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