package com.example.alcancia12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.alcancia12.adapter.MetaAdapter;
import com.example.alcancia12.model.Meta;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;


public class Metas extends DialogFragment {

    Button btn_add,btn_ver_metas;
    EditText proposito, costo;
    RecyclerView mRecycler;
    MetaAdapter metaAdapter;
    FirebaseFirestore mfirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_metas, container, false);

        mfirestore = FirebaseFirestore.getInstance();

        btn_ver_metas = v.findViewById(R.id.btn_ver_metas);
        costo = v.findViewById(R.id.inputCosto);
        proposito = v.findViewById(R.id.inputProposito);
        btn_add = v.findViewById(R.id.btn_add);
/*        mRecycler = v.findViewById(R.id.recyclerViewMeta);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = mfirestore.collection("Meta");*/

       /* FirestoreRecyclerOptions<Meta> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Meta>().setQuery(query, Meta.class).build();

        metaAdapter = new MetaAdapter(firestoreRecyclerOptions);
        metaAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(metaAdapter);*/

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prop = proposito.getText().toString().trim();
                String cos =  costo.getText().toString().trim();

                if(prop.isEmpty() && cos.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese los datos!", Toast.LENGTH_SHORT).show();
                }else{
                    insertarMeta(prop,cos);
                }
            }
        });

        btn_ver_metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntoMetas intoMetas = new IntoMetas();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, intoMetas).commit();
            }
        });

        return v;
    }


    private void insertarMeta(String prop, String cos){
        Map<String, Object> map = new HashMap<>();
        map.put("proposito", prop);
        map.put("costo", cos);

        mfirestore.collection("Meta").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Insertado Exitosamente", Toast.LENGTH_SHORT).show();
               //getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al insertar", Toast.LENGTH_SHORT).show();
            }
        });
    }


/*    @Override
    public void onStart() {
        super.onStart();
        metaAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        metaAdapter.stopListening();
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



/*        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("proposito",etProposito.getText().toString().trim());
                bundle.putString("costo",etCosto.getText().toString().trim());
                getParentFragmentManager().setFragmentResult("key",bundle);
            }
        });*/
    }


}