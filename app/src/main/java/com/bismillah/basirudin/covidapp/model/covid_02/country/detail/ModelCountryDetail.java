package com.bismillah.basirudin.covidapp.model.covid_02.country.detail;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ModelCountryDetail{

	@SerializedName("country")
	private String country;

	@SerializedName("latest_stat_by_country")
	private List<LatestStatByCountryItem> latestStatByCountry;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		if (country!=null)return country;
		else return "";
	}

	public void setLatestStatByCountry(List<LatestStatByCountryItem> latestStatByCountry){
		this.latestStatByCountry = latestStatByCountry;
	}

	public List<LatestStatByCountryItem> getLatestStatByCountry(){
		return latestStatByCountry;
	}

	@Override
 	public String toString(){
		return 
			"ModelCountryDetail{" + 
			"country = '" + country + '\'' + 
			",latest_stat_by_country = '" + latestStatByCountry + '\'' + 
			"}";
		}
}