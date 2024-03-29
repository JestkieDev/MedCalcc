package com.example.medcalc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PacientFragment extends Fragment {

    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pacient, container, false);
        TextView pnameTv = view.findViewById(R.id.pnameTV);
        TextView pageTv = view.findViewById(R.id.pageTV);
        TextView pimtTv = view.findViewById(R.id.pimtTV);
        TextView pinfTv = view.findViewById(R.id.pinfTV);
        TextView pcapTv = view.findViewById(R.id.pcapTV);
        TextView pdefTv = view.findViewById(R.id.pdefTV);
        DecimalFormat df = new DecimalFormat("###.##");
        String nme;
        mainDb = MainDb.getINSTANCE(getContext());
        pacientDao = mainDb.pacientDao();
        pacients = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            nme = bundle.getString("nme", "egor");
            pacients = pacientDao.getWithName(nme);
        }
        pnameTv.setText(pacients.get(0).name.toString());
        pageTv.setText(String.valueOf(pacients.get(0).age));
        pimtTv.setText("ИМТ: " + df.format(pacients.get(0).imt));
        pinfTv.setText("Скорость инфузии препарата: " + df.format(pacients.get(0).nf) + " мл/ч");
        pcapTv.setText("Скорость внутривенного капельного введения препарата: " + df.format(pacients.get(0).cap) + " капель в мин");
        pdefTv.setText("Дефицит: " + df.format(pacients.get(0).def) + " ммоль(мл)");
        if (pacients.get(0).def > 0){
            pdefTv.setText("Дефицит: " + df.format(pacients.get(0).def) + " ммоль(мл)");
        } else if (pacients.get(0).def == 0) {
            pdefTv.setText("Дефицит: " + "Нормокалиемия");
        } else if (pacients.get(0).def < 0) {
            pdefTv.setText("Дефицит: " + "Гиперкалиемия");
        }



        return view;
    }
}
