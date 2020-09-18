package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class NexoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continuarButton;
    private CheckBox opcion1check, opcion2check,opcion3check, opcion4check, opcion5check;
    private int puntaje = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexo);

        opcion1check = findViewById(R.id.opcion1Check);
        opcion2check = findViewById(R.id.opcion2Check);
        opcion3check = findViewById(R.id.opcion3Check);
        opcion4check = findViewById(R.id.opcion4Check);
        opcion5check = findViewById(R.id.opcion5Check);
        continuarButton= findViewById(R.id.continuarButton);

        continuarButton.setOnClickListener(this);
        opcion1check.setOnClickListener(this);
        opcion2check.setOnClickListener(this);
        opcion3check.setOnClickListener(this);
        opcion4check.setOnClickListener(this);
        opcion5check.setOnClickListener(this);
        continuarButton.setEnabled(false);
    }


    @Override
    public void onClick(View view) {

        boolean contacto = opcion1check.isChecked();
        boolean viaje = opcion2check.isChecked();
        boolean viajeNacional = opcion3check.isChecked();
        boolean trabajador = opcion4check.isChecked();
        boolean ninguno = opcion5check.isChecked();

        switch (view.getId()){

            case R.id.opcion1Check:
                opcion5check.setChecked(false);

                break;
            case R.id.opcion2Check:
                opcion5check.setChecked(false);

                break;
            case R.id.opcion3Check:
                opcion5check.setChecked(false);

                break;
            case R.id.opcion4Check:
                opcion5check.setChecked(false);

                break;
            case R.id.opcion5Check:
                continuarButton.setEnabled(true);
                opcion1check.setChecked(false);
                opcion2check.setChecked(false);
                opcion3check.setChecked(false);
                opcion4check.setChecked(false);
                break;

            case R.id.continuarButton:

                if(viaje == true){
                    puntaje += 3;
                }if(contacto == true){
                puntaje += 3;
            }if(viajeNacional == true){
                puntaje += 3;
            }if(trabajador == true){
                puntaje += 3;
            }

                Intent i = new Intent(this, SintomasActivity.class);
                startActivity(i);

                break;
        }

        if( contacto == true || viaje==true || viajeNacional==true||trabajador==true|| ninguno== true ){
            continuarButton.setEnabled(true);
        } else {
            continuarButton.setEnabled(false);
        }

        SharedPreferences puntajeNexo = getSharedPreferences("puntaje1",MODE_PRIVATE);
        puntajeNexo.edit().putInt("puntaje",puntaje).apply();
        Log.e("resultado",puntaje + " ");

    }
}