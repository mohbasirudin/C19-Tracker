package com.bismillah.basirudin.covidapp.model.covid_01.history;

public class Parameters{
	private String country;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	@Override
 	public String toString(){
		return 
			"Parameters{" + 
			"country = '" + country + '\'' + 
			"}";
		}
}
