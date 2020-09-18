package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashSet;
import java.util.Set;

public class SintomasActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continuarButton;
    private CheckBox opcion1Box,opcion2Box,opcion3Box,opcion4Box,opcion5Box,opcion6Box,opcion7Box;
    private int puntaje =0;
    private int  puntajeNexo, total;
    String name, ID;
    Set<String> encuestados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);

        opcion1Box=findViewById(R.id.opcion1Box);
        opcion2Box=findViewById(R.id.opcion2Box);
        opcion3Box=findViewById(R.id.opcion3Box);
        opcion4Box=findViewById(R.id.opcion4Box);
        opcion5Box=findViewById(R.id.opcion5Box);
        opcion6Box=findViewById(R.id.opcion6Box);
        opcion7Box=findViewById(R.id.opcion7Box);

        continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(this);
        opcion1Box.setOnClickListener(this);
        opcion2Box.setOnClickListener(this);
        opcion3Box.setOnClickListener(this);
        opcion4Box.setOnClickListener(this);
        opcion5Box.setOnClickListener(this);
        opcion6Box.setOnClickListener(this);
        opcion7Box.setOnClickListener(this);
        continuarButton.setEnabled(false);

        SharedPreferences preferences1 = getSharedPreferences("datos", MODE_PRIVATE);
        encuestados = preferences1.getStringSet("registrados", null);


    }


    @Override
    public void onClick(View view) {

        boolean fiebre = opcion1Box.isChecked();
        boolean dolorDeGarganta = opcion2Box.isChecked();
        boolean congestion = opcion3Box.isChecked();
        boolean tos  = opcion4Box.isChecked();
        boolean fatiga  = opcion5Box.isChecked();
        boolean respirar  = opcion6Box.isChecked();
        boolean ninguno  = opcion7Box.isChecked();

        switch(view.getId()){

            case R.id.opcion1Box:
                opcion7Box.setChecked(false);

                break;

            case R.id.opcion2Box:
                opcion7Box.setChecked(false);

                break;

            case R.id.opcion3Box:
                opcion7Box.setChecked(false);

                break;

            case R.id.opcion4Box:
                opcion7Box.setChecked(false);

                break;

            case R.id.opcion5Box:
                opcion7Box.setChecked(false);

                break;
            case R.id.opcion6Box:
                opcion7Box.setChecked(false);

                break;
            case R.id.opcion7Box:
                opcion1Box.setChecked(false);
                opcion2Box.setChecked(false);
                opcion3Box.setChecked(false);
                opcion4Box.setChecked(false);
                opcion5Box.setChecked(false);
                opcion6Box.setChecked(false);


                break;

            case R.id.continuarButton:

                if(fiebre == true){
                    puntaje += 4;
                } if(dolorDeGarganta == true){
                puntaje += 4;
            } if(congestion == true){
                puntaje += 4;
            } if(tos == true){
                puntaje += 4;
            } if(fatiga == true){
                puntaje += 4;
            } if(respirar == true){
                puntaje += 4;
            }

                total = puntajeNexo +puntaje;

                Log.e("puntaje",puntaje+ "");
                Log.e("TOTAL",total+"");


                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);

                break;

        }

        if( fiebre == true || dolorDeGarganta==true || congestion==true||tos == true|| fatiga == true || respirar == true || ninguno == true){
            continuarButton.setEnabled(true);
        } else {
            continuarButton.setEnabled(false);
        }

     if(name != null){

    encuestados.add(name + "  " + total);
      }



        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
        preferences.edit().putStringSet("registrados",encuestados).apply();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences puntajeRecibido = getSharedPreferences("puntaje1",MODE_PRIVATE);
        puntajeNexo = puntajeRecibido.getInt("puntaje",-1);

        SharedPreferences namePrefe = getSharedPreferences("registro",MODE_PRIVATE);
        name = namePrefe.getString("usuario","NO_NAME");
        ID = namePrefe.getString("id","NO_ID");



    }


}