package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView encuestadosText;
    private Button registrarButton;

    private Set<String> encuestados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encuestadosText = findViewById(R.id.encuestadosText);
        registrarButton = findViewById(R.id.registrarButton);
        registrarButton.setOnClickListener(this);
        encuestadosText.setText("");

        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
        encuestados = preferences.getStringSet("registrados", null);
        Lista();
    }

    public void Lista(){

        new Thread(
                ()->{
                    try {
                        Thread.sleep(500);
                        if(encuestados != null) {
                            for (String usuarios : encuestados) {
                                runOnUiThread(
                                        () -> {
                                            encuestadosText.append(usuarios + "\n");
                                        }
                                );
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        ).start();

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);

    }

}