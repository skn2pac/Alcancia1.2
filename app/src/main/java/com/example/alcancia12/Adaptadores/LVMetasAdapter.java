package com.example.alcancia12.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alcancia12.Models.Meta;
import com.example.alcancia12.R;

import java.util.ArrayList;

public class LVMetasAdapter extends BaseAdapter {
    Context context;
    ArrayList<Meta> metaData;
    LayoutInflater li;
    Meta metaModel;

    public LVMetasAdapter(Context context, ArrayList<Meta> metaData) {
        this.context = context;
        this.metaData = metaData;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return metaData.size();
    }

    @Override
    public Object getItem(int i) {
        return metaData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView==null){
            rowView = li.inflate(R.layout.lista_metas,null,true);
        }
        //enlazamos vistas
        TextView proposito = rowView.findViewById(R.id.proposito);
        TextView costo = rowView.findViewById(R.id.costo);
        TextView fechaRegistro = rowView.findViewById(R.id.fechaRegistro);

        metaModel = metaData.get(i);
        proposito.setText(metaModel.getProposito());
        costo.setText(metaModel.getCosto());
        fechaRegistro.setText(metaModel.getFecharegistro());

        return rowView;
    }
}
