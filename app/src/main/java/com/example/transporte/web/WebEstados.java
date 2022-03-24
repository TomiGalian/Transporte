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

public class WebEstados {

    private RequestQueue queue;


    public void conectarEstadoNuevo(Context context, Conductor conductor)  {

        queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.GET,
                urlEstadoNuevo(conductor),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            traductor(response,context);

                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                            Log.e("conectarEstadoNuevo","ERROR: catch :(");

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("conectarEstadoNuevo","Error del Respnese:" + error.toString());
                    }
                });
        stringRequest.setTag("Estado");


        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return termino;

    }

    private void traductor(String istream, Context context) throws XmlPullParserException, IOException {

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
                                //TODO: Tendriamos que hacer algo asi como que lo vuelva a mandar hasta que entre
                                //Porq tampoco queremos al conductor tocando el boton repetidamente porque no se conecta
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
            queue.cancelAll("Estado");
        }
    }

    private String urlEstadoNuevo(Conductor conductor) {  //TODO


        return "";


    }
}
