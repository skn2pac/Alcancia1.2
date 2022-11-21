package com.example.alcancia12.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcancia12.R;
import com.example.alcancia12.model.Meta;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MetaAdapter extends FirestoreRecyclerAdapter<Meta, MetaAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MetaAdapter(@NonNull FirestoreRecyclerOptions<Meta> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Meta meta) {
        holder.proposito.setText(meta.getProposito());
        holder.costo.setText(meta.getCosto());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_metas, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proposito, costo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            proposito = itemView.findViewById(R.id.proposito);
            costo = itemView.findViewById(R.id.costo);
        }
    }
}
