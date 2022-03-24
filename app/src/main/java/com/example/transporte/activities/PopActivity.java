package com.example.transporte.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transporte.R;
import com.example.transporte.modelo.Conductor;

import org.w3c.dom.Text;

import java.io.Serializable;


public class PopActivity extends Activity {

    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        Intent intent = getIntent();
        conductor = (Conductor) intent.getSerializableExtra("conductor");

        getActionBar().hide();

        Button btnAceptar = findViewById( R.id.btnAceptar );
        btnAceptar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO habria que avisarle al servidor si acepto o no el viaje??
                conductor.enCamino();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("conductor", (Serializable) conductor);
                startActivity(intent);
                finish();
            }
        });

        //TODO boton cancelar y/o click afuera no hace nada

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int largo = dm.heightPixels;

        getWindow().setLayout((int) (ancho*.8),(int)(largo*.35));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 200;

        getWindow().setAttributes(params);

        reciboViaje();
    }

    public void reciboViaje(){

        LinearLayout informacionPasajero = findViewById( R.id.infoPasajero );
        TextView nombre = findViewById( R.id.nombrePasajero );
        TextView recogidaRef = findViewById( R.id.recogidaReferencia );
        TextView destinoRef = findViewById( R.id.destinoReferencia );

        informacionPasajero.setVisibility(View.VISIBLE);
        nombre.setText(conductor.getViaje().getPasajero().getNombrePasajero());
        recogidaRef.setText(conductor.getViaje().getPasajero().getReferenciaOrigen());
        destinoRef.setText(conductor.getViaje().getPasajero().getReferenciaDestino());


    }
}
