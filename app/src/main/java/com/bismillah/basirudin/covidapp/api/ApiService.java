package com.bismillah.basirudin.covidapp.api;

import com.bismillah.basirudin.covidapp.BuildConfig;
import com.bismillah.basirudin.covidapp.model.covid_01.statistic.ModelStatistic;
import com.bismillah.basirudin.covidapp.model.covid_02.country.detail.ModelCountryDetail;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.ModelCountryList;
import com.bismillah.basirudin.covidapp.model.covid_02.total.ModelTotal;
import com.bismillah.basirudin.covidapp.model.covid_03.provinsi.ModelProvinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String host_01 = "covid-193.p.rapidapi.com",
            host_02 = "coronavirus-monitor.p.rapidapi.com";

    //    COVID 01
    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_01})
    @GET("statistics")
    Call<ModelStatistic> getStatistics();

    //    COVID 02
    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_02})
    @GET("worldstat.php")
    Call<ModelTotal> getTotal();

    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_02})
    @GET("cases_by_country.php")
    Call<ModelCountryList> getCountry();

    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_02})
    @GET("latest_stat_by_country.php")
    Call<ModelCountryDetail> getCountry(@Query("country") String country);

    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_02})
    @GET("cases_by_particular_country.php")
    Call<ModelCountryList> getParticular(@Query("country") String country);

    @Headers({"x-rapidapi-key: " + BuildConfig.api_rapid,
            "x-rapidapi-host: " + host_02})
    @GET("history_by_particular_country_by_date.php")
    Call<ModelCountryList> getParticular(@Query("country") String country,
                                         @Query("date") String date);

    //    COVID 03
    @GET("{country}/provinsi")
    Call<ModelProvinsi> getProvinsi(@Path("country") String country);
}
