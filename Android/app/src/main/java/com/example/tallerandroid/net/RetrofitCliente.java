package com.example.tallerandroid.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente {

    // Ruta para pruebas en emulador o localhost (solo PC)
    //private static final String BASE_URL = "http://10.0.2.2:8090/";


    // Ruta para pruebas desde un dispositivo físico en la misma red WiFi
    private static final String BASE_URL = "http://192.168.1.42:8090/";

    private static Retrofit retrofit = null;

    public static Retrofit getCliente(){
        if (retrofit ==  null) {
            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggin)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
