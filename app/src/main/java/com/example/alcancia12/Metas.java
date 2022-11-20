package com.example.alcancia12;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alcancia12.Models.Meta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Metas extends Fragment {


    public Metas(){
        // Required empty public constructor
    }

    private ArrayList<Meta> listMeta = new ArrayList<Meta>();
    ArrayAdapter<Meta> metaArrayAdapter;
    ListView lvMetas;
    LinearLayout linearLayoutEditar;

    EditText inputProposito, inputCosto;
    Button btnCancelar;

    Meta metaSeleccionada;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_metas, container, false);


        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_metas, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                String proposito = inputProposito.getText().toString();
                String costo = inputCosto.getText().toString();
                switch(menuItem.getItemId()){
                    case R.id.agregar:
                        insertar();
                        break;
                }

                return onMenuItemSelected(menuItem);
            }
        });

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputProposito = view.findViewById(R.id.inputProposito);
        inputCosto = view.findViewById(R.id.inputCosto);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        lvMetas = view.findViewById(R.id.lvMetas);
        linearLayoutEditar = view.findViewById(R.id.linearLayoutEditar);


        lvMetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                metaSeleccionada = (Meta) adapterView.getItemAtPosition(i);
                inputProposito.setText(metaSeleccionada.getProposito());
                inputCosto.setText(metaSeleccionada.getCosto());

                // volvemos visible el linearlayout
                linearLayoutEditar.setVisibility(View.VISIBLE);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutEditar.setVisibility(View.GONE);
                metaSeleccionada = null;
            }
        });

        inicializarFirebase();
        listarMetas();

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

    private void inicializarFirebase(){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    private void listarMetas(){
        databaseReference.child("Metas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMeta.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Meta m = objSnapshot.getValue(Meta.class);
                    listMeta.add(m);
                }
                metaArrayAdapter = new ArrayAdapter<Meta>(getContext(), android.R.layout.simple_list_item_1,listMeta);
                lvMetas.setAdapter(metaArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertar(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.metaspopup, null);
        Button btnInsertar = (Button) mView.findViewById(R.id.btnInsertar);
        final EditText mInputProposito = (EditText) mView.findViewById(R.id.inputProposito);
        final EditText mInputCosto = (EditText) mView.findViewById(R.id.inputCosto);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }

}