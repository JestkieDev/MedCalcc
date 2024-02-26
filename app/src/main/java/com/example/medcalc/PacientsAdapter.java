package com.example.medcalc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcalc.db.Pacient;
import com.example.medcalc.ui.PacientFragment;

import java.util.ArrayList;

public class PacientsAdapter extends RecyclerView.Adapter<PacientsAdapter.ViewHolder> {



    Context context;
    ArrayList<Pacient> pacients;

    public PacientsAdapter(Context context, ArrayList<Pacient> pacients) {
        this.context = context;
        this.pacients = pacients;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pacient pacient = pacients.get(position);
        holder.pacientnameTV.setText(pacient.name);
        holder.pacientageTV.setText(String.valueOf(pacient.age));
        String name = pacient.name;
        holder.pacientnameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                PacientFragment pacientFragment = new PacientFragment();
                bundle.putString("nme", name);
                pacientFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,pacientFragment).addToBackStack(null).commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return pacients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pacientnameTV;
        TextView pacientageTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pacientnameTV = itemView.findViewById(R.id.pacientname);
            pacientageTV = itemView.findViewById(R.id.pacientage);

        }
    }


}
