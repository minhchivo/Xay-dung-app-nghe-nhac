package org.meiocode.a1.service;

public class APIService {
    private static String BASE_URL = "https://appmusic2210.000webhostapp.com/Servce/";
    public static Dataservice getServices() {
        return APIRetrofitClient.getClient(BASE_URL).create(Dataservice.class);
    }
}

