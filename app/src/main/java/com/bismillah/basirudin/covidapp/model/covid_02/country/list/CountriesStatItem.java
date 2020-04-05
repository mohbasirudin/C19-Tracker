package com.bismillah.basirudin.covidapp.model.covid_02.country.list;


import com.google.gson.annotations.SerializedName;


public class CountriesStatItem {

    @SerializedName("cases")
    private String cases;

    @SerializedName("new_deaths")
    private String newDeaths;

    @SerializedName("new_cases")
    private String newCases;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("serious_critical")
    private String seriousCritical;

    @SerializedName("total_cases_per_1m_population")
    private String totalCasesPer1mPopulation;

    @SerializedName("total_recovered")
    private String totalRecovered;

    @SerializedName("region")
    private String region;

    @SerializedName("active_cases")
    private String activeCases;

    @SerializedName("deaths")
    private String deaths;

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getCases() {
        if (cases != null) {
            if (cases.isEmpty())
                cases = "0";
            return cases;
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
        } else return "";
    }

    public void setTotalCasesPer1mPopulation(String totalCasesPer1mPopulation) {
        this.totalCasesPer1mPopulation = totalCasesPer1mPopulation;
    }

    public String getTotalCasesPer1mPopulation() {
        if (totalCasesPer1mPopulation != null) {
            if (totalCasesPer1mPopulation.isEmpty())
                totalCasesPer1mPopulation = "0";
            return totalCasesPer1mPopulation;
        } else return "";
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

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        if (region != null) return region;
        else return "";
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getActiveCases() {
        if (activeCases != null) {
            if (activeCases.isEmpty())
                activeCases = "0";
            return activeCases;
        } else return "";
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getDeaths() {
        if (deaths != null) {
            if (deaths.isEmpty())
                deaths = "0";
            return deaths;
        } else return "";
    }

    @Override
    public String toString() {
        return
                "CountriesStatItem{" +
                        "cases = '" + cases + '\'' +
                        ",new_deaths = '" + newDeaths + '\'' +
                        ",new_cases = '" + newCases + '\'' +
                        ",country_name = '" + countryName + '\'' +
                        ",serious_critical = '" + seriousCritical + '\'' +
                        ",total_cases_per_1m_population = '" + totalCasesPer1mPopulation + '\'' +
                        ",total_recovered = '" + totalRecovered + '\'' +
                        ",region = '" + region + '\'' +
                        ",active_cases = '" + activeCases + '\'' +
                        ",deaths = '" + deaths + '\'' +
                        "}";
    }
}