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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medcalc.R;
import com.example.medcalc.db.MainDb;
import com.example.medcalc.db.Pacient;
import com.example.medcalc.db.PacientDao;
import com.example.medcalc.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class CapFragment extends Fragment {
    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cap, container, false);
        EditText capET = view.findViewById(R.id.capET);
        EditText mcapET = view.findViewById(R.id.mcapET);
        Button okcapBTN = view.findViewById(R.id.okcapBTN);
        TextView rescapTV = view.findViewById(R.id.rescapTV);
        Button backBTN = view.findViewById(R.id.backcapBTN);
        Spinner capSPN = view.findViewById(R.id.capSPN);
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
        capSPN.setAdapter(arrayAdapter);


        okcapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capET.getText().toString().isEmpty() == false & mcapET.getText().toString().isEmpty() == false) {
                    int cap = Integer.parseInt(capET.getText().toString());
                    int mcap = Integer.parseInt(mcapET.getText().toString());
                    int res = cap * 20/ mcap;
                    rescapTV.setText(String.valueOf(res) + " капель в мин");
                    if (capSPN.getSelectedItem().toString() != null){
                        List<Pacient> pacients1 = new ArrayList<>();
                        pacients1.addAll(pacientDao.getWithName(capSPN.getSelectedItem().toString()));
                        pacients1.get(0).cap = res;
                        pacientDao.update(pacients1.get(0));
                    } else {
                        Toast.makeText(getContext(), "Введите значения!", Toast.LENGTH_LONG).show();
                    }
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