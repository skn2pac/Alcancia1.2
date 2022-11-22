package com.example.alcancia12.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcancia12.IntoMetas;
import com.example.alcancia12.Metas;
import com.example.alcancia12.R;
import com.example.alcancia12.model.Meta;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MetaAdapter extends FirestoreRecyclerAdapter<Meta, MetaAdapter.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    FragmentManager fm;
    Activity activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MetaAdapter(@NonNull FirestoreRecyclerOptions<Meta> options, Activity activity, FragmentManager fm) {
        super(options);
        this.activity = activity;
        this.fm = fm;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Meta meta) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition());  // Posible cambio en getAbsoluteAdapterPosition / 'getAdapterPosition()' is deprecated
        final String id = documentSnapshot.getId();

        holder.proposito.setText(meta.getProposito());
        holder.costo.setText(meta.getCosto());

        //onclick img para editar
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Intent i = new Intent(activity, Metas.class);
                i.putExtra("id_meta",id);
                activity.startActivity(i);*/

                Metas metas = new Metas();
                Bundle bundle = new Bundle();
                bundle.putString("id_meta",id);
                metas.setArguments(bundle);
                metas.show(fm,"open fragment");

            }
        });

        //onclick img para borrar
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarMeta(id);
            }
        });
    }

    private void borrarMeta(String id) {
        mFirestore.collection("Meta").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Deleteado ", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_metas, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proposito, costo;
        ImageView img_delete, img_edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            proposito = itemView.findViewById(R.id.proposito);
            costo = itemView.findViewById(R.id.costo);
            img_delete = itemView.findViewById(R.id.img_eliminar);
            img_edit = itemView.findViewById(R.id.img_editar);
        }
    }
}
