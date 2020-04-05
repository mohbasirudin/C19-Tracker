package com.bismillah.basirudin.covidapp.api;

import com.bismillah.basirudin.covidapp.baseurl.Const;

public class ApiUtil {
    private static String covid_01 = "https://covid-193.p.rapidapi.com/";
    private static String covid_02 = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/";
    private static String covid_03 = "https://api.kawalcorona.com/";

    public static ApiService getService(String api) {
        String baseurl = "";
        switch (api) {
            case Const.covid_01:
                baseurl = covid_01;
                break;
            case Const.covid_02:
                baseurl = covid_02;
                break;
            case Const.covid_03:
                baseurl = covid_03;
                break;
        }
        return ApiClient.getClient(baseurl).create(ApiService.class);
    }
}
