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

public class ImtFragment extends Fragment {
    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imt, container, false);
        EditText mET = view.findViewById(R.id.mET);
        EditText hET = view.findViewById(R.id.hET);
        Button okimtBTN = view.findViewById(R.id.okimtBTN);
        TextView reimtTV = view.findViewById(R.id.resimtTV);
        Button backBTN = view.findViewById(R.id.backimtBTN);
        Spinner imtSPN = view.findViewById(R.id.imtSPN);
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
        imtSPN.setAdapter(arrayAdapter);






        okimtBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mass = Integer.parseInt(mET.getText().toString());
                double height = (double) Integer.parseInt(hET.getText().toString()) / 100;
                double res = mass/Math.pow(height, 2);
                DecimalFormat df = new DecimalFormat("###.#");
                reimtTV.setText(df.format(res));
                if (imtSPN.getSelectedItem().toString() != null){
                    List<Pacient> pacients1 = new ArrayList<>();
                    pacients1.addAll(pacientDao.getWithName(imtSPN.getSelectedItem().toString()));
                    pacients1.get(0).imt = res;
                    pacientDao.update(pacients1.get(0));
                }
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