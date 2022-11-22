package com.example.alcancia12;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alcancia12.adapter.MetaAdapter;
import com.example.alcancia12.model.Meta;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class IntoMetas extends Fragment {


    RecyclerView mRecycler;
    MetaAdapter metaAdapter;
    FirebaseFirestore mfirestore;
    ImageView img_delete;

    public IntoMetas() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_into_metas, container, false);

        //ImageView back = v.findViewById(R.id.back_into_metas);

        mRecycler = v.findViewById(R.id.recyclerViewMeta);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
/*        Query query = mfirestore.collection("Meta");*/

        FirestoreRecyclerOptions<Meta> options =
                new FirestoreRecyclerOptions.Builder<Meta>().setQuery(FirebaseFirestore.getInstance().collection("Meta"), Meta.class).build();

        metaAdapter = new MetaAdapter(options,this.getActivity());
/*        metaAdapter.notifyDataSetChanged();*/
        mRecycler.setAdapter(metaAdapter);


/*        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetach();
            }
        });*/

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