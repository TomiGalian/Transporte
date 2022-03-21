package com.example.transporte.web;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transporte.modelo.Conductor;
import com.example.transporte.web.WebNuevoViaje;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class WebGeolocalizacion {

    private RequestQueue queue;


    public void conectarCoordenadas(Context context, Conductor conductor)  {
        /**
         * SI LE PASAMOS EL CONTEXTO DE LA APP EN VEZ DEL DE LA ACTIVITY VA A SEGUIR FUNCIONANDO AUNQUE DEJE LA ACTIVITY
         *
         *  PATRON singleton: https://developer.android.com/training/volley/requestqueue?hl=es-419
         */

        queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.GET,
                urlCoordenadaActual(conductor),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            traductor(response,context,conductor);

                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                            Log.e("conectarCoordenadas","ERROR: catch :(");

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("conectarCoordenadas","Error del Respnese:" + error.toString());
                    }
                });
        stringRequest.setTag("Coordenadas");


        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return termino;

    }

    private void traductor(String istream, Context context, Conductor conductor) throws XmlPullParserException, IOException {

        String tag;
        String dato = "";
        boolean tabla = false;

        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        parserFactory.setNamespaceAware( true );
        XmlPullParser parser = parserFactory.newPullParser();
        parser.setInput(new StringReader(istream));
        int event = parser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT){
            tag = parser.getName();
            switch (event){
                case XmlPullParser.START_TAG:
                    if(tag.equals( "AAAAAAAAAAAAA" )) {
                        tabla = true;
                    }

                    break;

                case XmlPullParser.TEXT:
                    dato=parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if(tabla){
                        switch (tag) {
                            case "HOLA":
                                //LLamar a conectar nuevo viaje
                                WebNuevoViaje nuevoviaje = new WebNuevoViaje();
                                nuevoviaje.conectarNuevoViaje( context ,conductor);

                                break;
                            case "AAAAAAAAAAAAAA":
                                //cerrar
                                break;
                        }    

                    }


                    break;
            }
            event = parser.next();
        }
        if (queue != null) {
            queue.cancelAll("Coordenadas");
        } 
    }

    private String urlCoordenadaActual(Conductor conductor) {
        String url="";
        url+= conductor.getUbicacion().getLatitud();
        url+= conductor.getUbicacion().getLongitud();

        return "";


    }
}


