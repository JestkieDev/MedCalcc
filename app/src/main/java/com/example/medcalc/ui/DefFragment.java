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

public class DefFragment extends Fragment {
    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_def, container, false);
        EditText calET = view.findViewById(R.id.calET);
        EditText mdET = view.findViewById(R.id.mdET);
        Button okdefBTN = view.findViewById(R.id.okdefBTN);
        TextView resdefTV = view.findViewById(R.id.resdefTV);
        Button backBTN = view.findViewById(R.id.backdefBTN);
        Spinner defSPN = view.findViewById(R.id.defSPN);
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
        defSPN.setAdapter(arrayAdapter);


        okdefBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cal = Double.parseDouble(calET.getText().toString());
                int md = Integer.parseInt(mdET.getText().toString());
                double res = (double) (5 - cal) * 0.2 * md;
                DecimalFormat df = new DecimalFormat("###.#");
                if (res > 0){
                    resdefTV.setText(df.format(res) + " ммоль(мл)");
                } else if (res == 0) {
                    resdefTV.setText("Нормокалиемия");
                } else if (res < 0) {
                    resdefTV.setText("Гиперкалиемия");
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