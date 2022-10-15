package com.example.alcancia12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class Principal1 extends AppCompatActivity {


    int contador;
    Button boton1;
    TextView caja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal1);




        //Tolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //tablayout
        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Codificar cosas a ejecutar cuando le das tap a un tab
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        //Llamar al fragmento inicio
                        MenuPrincipal mp = new MenuPrincipal();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,mp).commit();
                        getSupportActionBar().setTitle(R.string.menu_principal);
                        break;
                    case 1:
                        //Llamar al fragmento ahorros
                        Ahorros a = new Ahorros();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,a).commit();
                        break;
                    case 2:
                        //Llamar al fragmento estadisticas
                        Estadisticas e = new Estadisticas();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,e).commit();
                        break;
                    case 3:
                        //Llamar al fragmento metas
                        Metas m = new Metas();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,m).commit();
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Codificar cosas a ejecutar cuando un tab deja de estar seleccionado
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Codificar cosas a ejecutar cuando un tab se vuelve a seleccionar
            }
        });

        // Contador



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Incorporar el menu dentro de la activity
        getMenuInflater().inflate(R.menu.menu1,menu);

        return true;

    }



    // DESCOMENTAR CUANDO SE ARREGLE EL TOOLBAR
    //     @Override
    //    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //        int id = item.getItemId(); //recuperar el id de la opcion seleccionada
    //
    //        if(id==R.id.op2){
    //            Toast.makeText(this, "Vas a los ajustes", Toast.LENGTH_SHORT).show();
    //        }
    //
    //
    //
    //        return super.onOptionsItemSelected(item);
    //    }




}