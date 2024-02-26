package com.example.medcalc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medcalc.R;
import com.example.medcalc.db.MainDb;
import com.example.medcalc.db.Pacient;
import com.example.medcalc.db.PacientDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InfFragment extends Fragment {
    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;
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
        Spinner infSPN = view.findViewById(R.id.infSPN);
        mainDb = MainDb.getINSTANCE(getContext());
        ArrayList<String> pacientNames = new ArrayList<>();
        pacientDao = mainDb.pacientDao();
        pacients = new ArrayList<>();
        if (pacientDao.getAll() != null){
            pacients = pacientDao.getAll();
        }
        for (int i = 0; i < pacients.size(); i++){
            pacientNames.add(pacients.get(i).name);
        }
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter =  new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pacientNames);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        infSPN.setAdapter(arrayAdapter);

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