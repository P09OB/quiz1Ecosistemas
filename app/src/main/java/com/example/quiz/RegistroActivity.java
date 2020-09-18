package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameEdit;
    private EditText IDedit;
    private Button registrarButton;
    String name, identificacion, idsAnteriores;
    Set<String> encuestados;
    String[] ids;
    boolean pasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        userNameEdit = findViewById(R.id.userNameEdit);
        IDedit = findViewById(R.id.IDedit);
        registrarButton = findViewById(R.id.registrarButton);
        registrarButton.setOnClickListener(this);
        pasa=true;

    }

    @Override
    public void onClick(View view) {

        name = userNameEdit.getText().toString();
        identificacion = IDedit.getText().toString();
        ids = idsAnteriores.split(" ");

        if(name.isEmpty() && identificacion.isEmpty()){
            pasa=true;
            Toast.makeText(this,"Llene todos los campos",Toast.LENGTH_LONG).show();
            return;
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i].contains(identificacion)) {
                pasa = false;
                Toast.makeText(this,"ID ya fue registrado",Toast.LENGTH_LONG).show();
            }
        }


        if (pasa == true) {

            Intent i = new Intent(this, NexoActivity.class);
            startActivity(i);
        }

        SharedPreferences namePrefe = getSharedPreferences("registro", MODE_PRIVATE);
        namePrefe.edit().putString("usuario", name).apply();
        namePrefe.edit().putString("id", identificacion).apply();

    }




    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
        encuestados = preferences.getStringSet("registrados", null);

        SharedPreferences registro = getSharedPreferences("registro",MODE_PRIVATE);
        idsAnteriores = registro.getString("id","NO_ID");

    }
}