package com.bismillah.basirudin.covidapp.model.covid_02.country.list;

import java.util.List;



import com.google.gson.annotations.SerializedName;


public class ModelCountryList {

    @SerializedName("countries_stat")
    private List<CountriesStatItem> countriesStat;

    @SerializedName("statistic_taken_at")
    private String statisticTakenAt;

    public void setCountriesStat(List<CountriesStatItem> countriesStat) {
        this.countriesStat = countriesStat;
    }

    public List<CountriesStatItem> getCountriesStat() {
        return countriesStat;
    }

    public void setStatisticTakenAt(String statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }

    public String getStatisticTakenAt() {
        if (statisticTakenAt != null) return statisticTakenAt;
        else return "";
    }

    @Override
    public String toString() {
        return
                "ModelCountryList{" +
                        "countries_stat = '" + countriesStat + '\'' +
                        ",statistic_taken_at = '" + statisticTakenAt + '\'' +
                        "}";
    }
}