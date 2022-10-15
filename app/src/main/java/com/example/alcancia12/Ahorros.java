package com.example.alcancia12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ahorros#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ahorros extends Fragment {






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int count = 0;
    Button btnCount;
    TextView txtCount;



    public Ahorros() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ahorros.
     */
    // TODO: Rename and change types and number of parameters
    public static Ahorros newInstance(String param1, String param2) {
        Ahorros fragment = new Ahorros();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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