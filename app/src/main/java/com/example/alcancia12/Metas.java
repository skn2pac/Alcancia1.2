package com.example.alcancia12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Metas extends Fragment {


    public Metas(){
        // Required empty public constructor
    }

    EditText etProposito, etCosto;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_metas, container, false);



        //Metodo con boton para llamar a ver metas
        Button btn_ver_metas=(Button) view.findViewById(R.id.btn_ver_metas);
        btn_ver_metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr= getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.contenedor,new IntoMetas()).commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etProposito = view.findViewById(R.id.etProposito);
        etCosto = view.findViewById(R.id.etCosto);
        button = view.findViewById(R.id.buttonSave);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("proposito",etProposito.getText().toString().trim());
                bundle.putString("costo",etCosto.getText().toString().trim());
                getParentFragmentManager().setFragmentResult("key",bundle);
            }
        });
    }
}