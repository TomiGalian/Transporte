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
import java.util.UUID;


public class PopActivity extends Activity {

    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        Intent intent = getIntent();
        conductor = (Conductor) intent.getSerializableExtra("conductor");

        getActionBar().hide();

        this.setFinishOnTouchOutside(false); //GODDDD LO DESCUBRI RE DE PEDO AJAJAJAJ


        Button btnAceptar = findViewById( R.id.btnAceptar );
        btnAceptar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO habria que avisarle al servidor si acepto o no el viaje
                conductor.enCamino();
                Intent intent = new Intent();
                intent.putExtra("conductor", (Serializable) conductor);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        Button btnCancelar = findViewById(R.id.btnRechazar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO hay que darle motivos de por que cancela o solo si esta en camino?
                //conductor.borrarViaje(); habria que borrar el viaje porque ya lo guardo no?
                finish();
            }
        });

        //TODO boton cancelar y/o click afuera no hace nada, y que si cancela diga porque (Voy a cargar combustible, pinché una rueda, se descompuso Vehículo, etc )

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int largo = dm.heightPixels;

        getWindow().setLayout((int) (ancho*.8),(int)(largo*.35));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 200;

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

    @Override
    public void onBackPressed() {/*XD*/}
}
