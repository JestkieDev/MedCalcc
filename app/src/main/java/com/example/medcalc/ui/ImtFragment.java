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

public class ImtFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imt, container, false);
        EditText mET = view.findViewById(R.id.mET);
        EditText hET = view.findViewById(R.id.hET);
        Button okimtBTN = view.findViewById(R.id.okimtBTN);
        TextView reimtTV = view.findViewById(R.id.resimtTV);
        Button backBTN = view.findViewById(R.id.backimtBTN);

        okimtBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mass = Integer.parseInt(mET.getText().toString());
                double height = (double) Integer.parseInt(hET.getText().toString()) / 100;
                double res = mass/Math.pow(height, 2);
                DecimalFormat df = new DecimalFormat("###.#");
                reimtTV.setText(df.format(res));
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