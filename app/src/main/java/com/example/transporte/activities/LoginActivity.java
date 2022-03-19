package com.example.transporte.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transporte.R;
import com.example.transporte.modelo.Conductor;
import com.example.transporte.modelo.Viaje;
import com.example.transporte.web.WebInicioSesion;

public class LoginActivity extends AppCompatActivity {


    Conductor conductor;
    Button inicioSesion;
    EditText usr, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebInicioSesion clase = new WebInicioSesion(); //Esta bien???

        inicioSesion = findViewById( R.id.btnIniciar );
        usr     =   findViewById( R.id.editTUsuario );
        pass    =   findViewById( R.id.editTContraseña );

        inicioSesion.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        conductor = new Conductor(usr.getText().toString(),pass.getText().toString());
                        Log.e("Usuario", usr.getText().toString());
                        Log.e("Password", pass.getText().toString());
                        clase.conectarInicioSesion( getApplicationContext(), conductor );

                    }
                });
    }


}