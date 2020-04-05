package com.bismillah.basirudin.covidapp.model.covid_02.Particular.list;


import com.google.gson.annotations.SerializedName;


public class StatByCountryItem {

    @SerializedName("total_cases")
    private String totalCases;

    @SerializedName("record_date")
    private String recordDate;

    @SerializedName("total_deaths")
    private String totalDeaths;

    @SerializedName("new_cases")
    private String newCases;

    @SerializedName("new_deaths")
    private String newDeaths;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("serious_critical")
    private String seriousCritical;

    @SerializedName("id")
    private String id;

    @SerializedName("total_recovered")
    private String totalRecovered;

    @SerializedName("active_cases")
    private String activeCases;

    @SerializedName("region")
    private String region;

    @SerializedName("total_cases_per1m")
    private String totalCasesPer1m;

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTotalCases() {
        if (totalCases != null) {
            if (totalCases.isEmpty())
                totalCases = "0";
            return totalCases;
        } else return "0";
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordDate() {
        if (recordDate != null) return recordDate;
        else return "";
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTotalDeaths() {
        if (totalDeaths != null) {
            if (totalDeaths.isEmpty())
                totalDeaths = "0";
            return totalDeaths;
        } else return "0";
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getNewCases() {
        if (newCases != null) {
            if (newCases.isEmpty())
                newCases = "0";
            return newCases;
        } else return "0";
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewDeaths() {
        if (newDeaths != null) {
            if (newDeaths.isEmpty())
                newDeaths = "0";
            return newDeaths;
        } else return "0";
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        if (countryName != null) return countryName;
        else return "";
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public String getSeriousCritical() {
        if (seriousCritical != null) {
            if (seriousCritical.isEmpty())
                seriousCritical = "0";
            return seriousCritical;
        } else return "0";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        if (id != null) return id;
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
        } else return "0";
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getActiveCases() {
        if (activeCases != null) {
            if (activeCases.isEmpty())
                activeCases = "0";
            return activeCases;
        } else return "0";
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        if (region != null) return region;
        else return "";
    }

    public void setTotalCasesPer1m(String totalCasesPer1m) {
        this.totalCasesPer1m = totalCasesPer1m;
    }

    public String getTotalCasesPer1m() {
        if (totalCasesPer1m != null) {
            if (totalCasesPer1m.isEmpty())
                totalCasesPer1m = "0";
            return totalCasesPer1m;
        } else return "0";
    }

    @Override
    public String toString() {
        return
                "StatByCountryItem{" +
                        "total_cases = '" + totalCases + '\'' +
                        ",record_date = '" + recordDate + '\'' +
                        ",total_deaths = '" + totalDeaths + '\'' +
                        ",new_cases = '" + newCases + '\'' +
                        ",new_deaths = '" + newDeaths + '\'' +
                        ",country_name = '" + countryName + '\'' +
                        ",serious_critical = '" + seriousCritical + '\'' +
                        ",id = '" + id + '\'' +
                        ",total_recovered = '" + totalRecovered + '\'' +
                        ",active_cases = '" + activeCases + '\'' +
                        ",region = '" + region + '\'' +
                        ",total_cases_per1m = '" + totalCasesPer1m + '\'' +
                        "}";
    }
}