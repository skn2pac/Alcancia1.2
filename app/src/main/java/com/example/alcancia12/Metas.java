package com.example.alcancia12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Metas extends DialogFragment {

    Button btn_add,btn_ver_metas;
    EditText proposito, costo;
    FirebaseFirestore firebaseFirestore;
    String id_meta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            id_meta = getArguments().getString("id_meta");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_metas, container, false);

        String id = getActivity().getIntent().getStringExtra("id");

        firebaseFirestore = FirebaseFirestore.getInstance();

        btn_ver_metas = v.findViewById(R.id.btn_ver_metas);
        costo = v.findViewById(R.id.inputCosto);
        proposito = v.findViewById(R.id.inputProposito);
        btn_add = v.findViewById(R.id.btn_add);

        // Si el id del documento es nulo o esta vacio, saltara un aviso para ingresar los datos, en caso contrario este insertara los datos a la base de datos
        // esta funcion se activa en un listener del boton add

        if(id_meta==null || id_meta==""){
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prop = proposito.getText().toString().trim();
                    String cos = costo.getText().toString().trim();
                    long tiempo = getCurrentDateInMilliseconds();
                    if(prop.isEmpty() && cos.isEmpty()){
                        Toast.makeText(getContext(), "Ingresa los datos!!", Toast.LENGTH_SHORT).show();
                    }else{
                        insertarMeta(prop,cos,tiempo);
                    }
                }
            });
            // En caso contrario colocamos el actualizar que es llamado al utilizar el boton tipo imagen en el fragmento intometas
        }else{
            btn_add.setText("Actualizar");
            getMeta(id_meta);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prop = proposito.getText().toString().trim();
                    String cos = costo.getText().toString().trim();

                    if(prop.isEmpty() && cos.isEmpty()){
                        Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        updateMeta(prop,cos);
                    }
                }
            });
        }

        // Llamamos al fragmento IntoMetas con el boton ver metas dentro del fragmento Metas
        btn_ver_metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntoMetas intoMetas = new IntoMetas();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, intoMetas).commit();
            }
        });

        return v;
    }

    //Creamos la funcion updateMeta para mapear los valores de la base de datos
    private void updateMeta(String prop, String cos) {
        Map<String, Object> map = new HashMap<>();
        map.put("proposito", prop);
        map.put("costo", cos);

        // Buscamos en la coleccion "Meta" el "id_meta" el cual contiene los datos que se van a actualizar
        // Si al actualizar los datos estos cambian en la base de datos, con un toast le diremos al usuario que fue Actualizado exitosamente
        // En caso contrario, con un Toast le daremos a conocer que tuvo un error al actualizar y no se actualizaran los datos.
        firebaseFirestore.collection("Meta").document(id_meta).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "Updateado Exitosamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al updatear", Toast.LENGTH_SHORT).show();

            }
        });
    }


    // Hacemos uso de los get creados en el model Meta, para recuperar los datos y pasarlos como funcion al boton actualizar
    // Con esto llenamos los campos en el fragmento que se abrira para actualizarl los datos si es solicitado
    private void getMeta(String id_meta){
        firebaseFirestore.collection("Meta").document(id_meta).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String propositoMeta = documentSnapshot.getString("proposito");
                String costoMeta = documentSnapshot.getString("costo");
                proposito.setText(propositoMeta);
                costo.setText(costoMeta);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "No se pueden obtener los datos", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void insertarMeta(String prop, String cos,Long tiempo){
        Map<String, Object> map = new HashMap<>();
        map.put("proposito", prop);
        map.put("costo", cos);
        map.put("tiempo",tiempo);

        firebaseFirestore.collection("Meta").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Insertado Exitosamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al insertar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Tomamos el tiempo para incorporarlo en el insert y quede registro del tiempo en la base de datos
    private long getCurrentDateInMilliseconds() { return Calendar.getInstance().getTimeInMillis();}

    // Es para dar formato a los milisegundos en tiempo facil de leer para el usuario
    private String[] longIntoString(long milliseconds){
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return new String[]{dateFormat.format(milliseconds),timeFormat.format(milliseconds)};
    }



}