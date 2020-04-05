package com.bismillah.basirudin.covidapp.model.covid_03.api;

import com.bismillah.basirudin.covidapp.baseurl.Const;

public class ApiUtil {
    private static String baseurl = "https://api.kawalcorona.com/indonesia/";

    public static ApiService getService() {
        return ApiClient.getClient(baseurl).create(ApiService.class);
    }
}
