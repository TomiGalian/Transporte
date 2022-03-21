package com.example.transporte.web;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transporte.modelo.Conductor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class WebNuevoViaje {

    private RequestQueue queue;


    public void conectarNuevoViaje(Context context, Conductor conductor)  {
        /**
         * SI LE PASAMOS EL CONTEXTO DE LA APP EN VEZ DEL DE LA ACTIVITY VA A SEGUIR FUNCIONANDO AUNQUE DEJE LA ACTIVITY
         *
         *  PATRON singleton: https://developer.android.com/training/volley/requestqueue?hl=es-419
         */

        queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.GET,
                urlNuevoViaje(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            traductor(response,context,conductor);
                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                            Log.e("conectarNuevoViaje","ERROR: catch :(");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("conectarNuevoViaje","Error del Respnese:" + error.toString());
                    }
                });
        stringRequest.setTag("NuevoViaje");
        queue.add(stringRequest);

    }

    private void traductor(String istream, Context context, Conductor conductor) throws XmlPullParserException, IOException {

        String tag;
        String dato = "";
        boolean tabla = false;
        ArrayList<String> coleccionDatos = new ArrayList<String>();

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
                            case "nombre":
                                coleccionDatos.add(0, dato) ;
                                break;
                            case "recogida":
                                coleccionDatos.add(1, dato) ;
                                break;
                            case "referenciaRecogida":
                                coleccionDatos.add(2, dato) ;
                                break;
                            case "destino":
                                coleccionDatos.add(3, dato) ;
                                break;
                            case "referenciaDestino":
                                coleccionDatos.add(4, dato) ;
                                break;
                            case "AAAAAAAAAAAAAA":
                                //conductor.nuevoViaje(coleccionDatos.get( 0 ),coleccionDatos.get( 1 ),coleccionDatos.get( 2 ),coleccionDatos.get( 3 ),coleccionDatos.get( 4 ));

                                coleccionDatos.clear();
                                break;
                        }

                    }


                    break;
            }
            event = parser.next();
        }
        if (queue != null) {
            queue.cancelAll("NuevoViaje");
        }
    }

    private String urlNuevoViaje() {
        return "";


    }

}
