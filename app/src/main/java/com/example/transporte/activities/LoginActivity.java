package com.example.transporte.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transporte.R;
import com.example.transporte.modelo.Conductor;
import com.example.transporte.modelo.Viaje;
import com.example.transporte.web.WebInicioSesion;

import java.io.Serializable;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {


    Conductor conductor;
    Button inicioSesion;
    EditText usr, pass;
    String UUID;

    // TODO tema versiones de android , POP-UP + Multitareas bug?



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        obtenerClaveUnica();
        Log.e("ClaveUnica",UUID);

        WebInicioSesion clase = new WebInicioSesion();

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
                        //clase.conectarInicioSesion( getApplicationContext(), conductor, UUID );
                        irAlMain();

                    }
                });

    }

    private void obtenerClaveUnica (){
        SharedPreferences prefs = getSharedPreferences("UUID", MODE_PRIVATE);
        String clave = prefs.getString("clave", "");
        Log.e("ClaveRecibida",clave);
        if(clave.isEmpty()){
            UUID = java.util.UUID.randomUUID().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("clave",UUID);
            editor.apply();
        }
        else{
            UUID = clave;
        }
    }

    //          OJO ACA QUE EL INTENT TIENE QUE IR SI EL WEB RESPONDIO TODO OK
    //          POR AHORA LO DEJAMOS PARA PROBAR PERO HAY QUE SACARLO DSP
    private void irAlMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("conductor", (Serializable) conductor );
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();

    }


}