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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transporte.R;
import com.example.transporte.modelo.Conductor;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.UUID;


public class PopCancelarViajeActivity extends Activity {

    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_cancelar_viaje);

        //getActionBar().hide();

        this.setFinishOnTouchOutside(false); //GODDDD LO DESCUBRI RE DE PEDO AJAJAJAJ

        Button btnAceptar = findViewById( R.id.btnAceptar2 );
        CheckBox chOpcionA = findViewById( R.id.checkbox_a );
        CheckBox chOpcionB = findViewById( R.id.checkbox_b );
        CheckBox chOpcionC = findViewById( R.id.checkbox_c );
        CheckBox chOpcionD = findViewById( R.id.checkbox_d );
        EditText etRazon = findViewById( R.id.editTRazon );
        etRazon.setVisibility( View.INVISIBLE );

        btnAceptar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("motivo","motivo");
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        chOpcionA.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        chOpcionD.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etRazon.setVisibility( View.VISIBLE );
            }
        });




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int largo = dm.heightPixels;

        getWindow().setLayout((int) (ancho*.8),(int)(largo*.35));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 200;

        cancelarViaje();

    }

    public void cancelarViaje(){

        LinearLayout cancelacionViaje = findViewById( R.id.cancelacionViaje );
        //ListView motivos = findViewById(R.id.motivos);

        cancelacionViaje.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {/*XD*/}
}
