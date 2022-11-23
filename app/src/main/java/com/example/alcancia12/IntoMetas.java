package com.example.alcancia12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alcancia12.adapter.MetaAdapter;
import com.example.alcancia12.model.Meta;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;



public class IntoMetas extends Fragment {

    RecyclerView mRecycler;
    MetaAdapter metaAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_into_metas, container, false);

        mRecycler = v.findViewById(R.id.recyclerViewMeta);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FirestoreRecyclerOptions<Meta> options =
                new FirestoreRecyclerOptions.Builder<Meta>().setQuery(FirebaseFirestore.getInstance().collection("Meta"), Meta.class).build();

        metaAdapter = new MetaAdapter(options,this.getActivity(), getActivity().getSupportFragmentManager());
        mRecycler.setAdapter(metaAdapter);



        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        metaAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        metaAdapter.stopListening();
    }

}