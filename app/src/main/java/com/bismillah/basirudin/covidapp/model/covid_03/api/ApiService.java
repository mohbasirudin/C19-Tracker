package com.bismillah.basirudin.covidapp.model.covid_03.api;

import com.bismillah.basirudin.covidapp.model.covid_03.provinsi.ModelProvinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("provinsi")
    Call<List<ModelProvinsi>> getProvinsi();
}
