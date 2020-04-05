package com.bismillah.basirudin.covidapp.model.covid_02.Particular.detail;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ModelParticularDetail{

	@SerializedName("country")
	private String country;

	@SerializedName("stat_by_country")
	private List<StatByCountryItem> statByCountry;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		if (country!=null)return country;
		else return "";
	}

	public void setStatByCountry(List<StatByCountryItem> statByCountry){
		this.statByCountry = statByCountry;
	}

	public List<StatByCountryItem> getStatByCountry(){
		return statByCountry;
	}

	@Override
 	public String toString(){
		return 
			"ModelParticularDetail{" + 
			"country = '" + country + '\'' + 
			",stat_by_country = '" + statByCountry + '\'' + 
			"}";
		}
}