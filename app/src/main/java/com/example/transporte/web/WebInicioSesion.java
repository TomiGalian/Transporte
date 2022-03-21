package com.example.transporte.web;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transporte.activities.MainActivity;
import com.example.transporte.modelo.Conductor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;

public class WebInicioSesion {

    private RequestQueue queue;


    public void conectarInicioSesion(Context context, Conductor conductor)  {

        queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.GET,
                urlInicioSesion(conductor),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            traductor(response,context,conductor);

                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                            Log.e("conectarInicioSesion","ERROR: catch :(");

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("conectarInicioSesion","Error del Respnese:" + error.toString());
                    }
                });
        stringRequest.setTag("InicioSesion");


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
                            case "Error":

                                break;
                            case "Descr":
                                /**
                                 * SI FUNCIONA BIEN
                                 */
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("conductor", (Serializable) conductor);
                                context.startActivity(intent);

                                break;
                        }

                    }


                    break;
            }
            event = parser.next();
        }
        if (queue != null) {
            queue.cancelAll("InicioSesion");
        }
    }

    private String urlInicioSesion(Conductor conductor) {
        String url="";
        url += conductor.getUsuario();
        url += conductor.getContraseña();

        return url;
        // Deberia poder llamar a conductor



    }
}
