package com.example.alcancia12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Ahorros extends Fragment {


    int count = 0;
    Button btnCount;
    TextView txtCount;


    public Ahorros() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View componenteFragment = inflater.inflate(R.layout.fragment_ahorros, container, false);

        txtCount = (TextView)componenteFragment.findViewById(R.id.monto_ahorros);
        btnCount = (Button)componenteFragment.findViewById(R.id.boton_agregar);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 500;
                txtCount.setText(String.valueOf(count));
            }
        });

        return componenteFragment;



    }
}