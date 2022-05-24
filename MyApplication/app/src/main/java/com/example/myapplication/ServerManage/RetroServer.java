package com.example.myapplication.ServerManage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String BaseURL = "http://192.168.0.109/RestAPI/Restoran/";
    private static Retrofit retrofit;

    public final Retrofit KonekToDB(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

       return retrofit;
    }
}
