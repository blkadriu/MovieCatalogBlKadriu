package com.example.moviecatalog;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singelton {

    private final RequestQueue requestQueue;
    private static Singelton mInstance;

    public Singelton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized Singelton getSingletonInstance(Context context){
        if(mInstance == null)
            mInstance = new Singelton(context);

        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
