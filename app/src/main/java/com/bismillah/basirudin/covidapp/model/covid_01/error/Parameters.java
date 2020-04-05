package com.bismillah.basirudin.covidapp.model.covid_01.error;
import com.google.gson.annotations.SerializedName;
public class Parameters{

	@SerializedName("country")
	private String country;

	@SerializedName("day")
	private String day;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Parameters{" + 
			"country = '" + country + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}