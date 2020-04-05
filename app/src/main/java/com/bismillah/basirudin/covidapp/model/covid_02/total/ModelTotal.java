package com.bismillah.basirudin.covidapp.model.covid_02.total;


import com.google.gson.annotations.SerializedName;


public class ModelTotal {

    @SerializedName("total_cases")
    private String totalCases;

    @SerializedName("total_deaths")
    private String totalDeaths;

    @SerializedName("new_cases")
    private String newCases;

    @SerializedName("new_deaths")
    private String newDeaths;

    @SerializedName("statistic_taken_at")
    private String statisticTakenAt;

    @SerializedName("total_recovered")
    private String totalRecovered;

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTotalCases() {
        if (totalCases != null) {
            if (totalCases.isEmpty())
                totalCases = "0";
            return totalCases;
        } else return "";
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTotalDeaths() {
        if (totalDeaths != null) {
            if (totalDeaths.isEmpty())
                totalDeaths = "0";
            return totalDeaths;
        } else return "";
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getNewCases() {
        if (newCases != null) {
            if (newCases.isEmpty())
                newCases = "0";
            return newCases;
        } else return "";
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewDeaths() {
        if (newDeaths != null) {
            if (newDeaths.isEmpty())
                newDeaths = "0";
            return newDeaths;
        } else return "";
    }

    public void setStatisticTakenAt(String statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }

    public String getStatisticTakenAt() {
        if (statisticTakenAt != null) return statisticTakenAt;
        else return "";
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getTotalRecovered() {
        if (totalRecovered != null) {
            if (totalRecovered.isEmpty())
                totalRecovered = "0";
            return totalRecovered;
        } else return "";
    }

    @Override
    public String toString() {
        return
                "ModelTotal{" +
                        "total_cases = '" + totalCases + '\'' +
                        ",total_deaths = '" + totalDeaths + '\'' +
                        ",new_cases = '" + newCases + '\'' +
                        ",new_deaths = '" + newDeaths + '\'' +
                        ",statistic_taken_at = '" + statisticTakenAt + '\'' +
                        ",total_recovered = '" + totalRecovered + '\'' +
                        "}";
    }
}