package com.bismillah.basirudin.covidapp.model.covid_01.history;

public class ResponseItem{
	private String country;
	private Cases cases;
	private String time;
	private String day;
	private Deaths deaths;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCases(Cases cases){
		this.cases = cases;
	}

	public Cases getCases(){
		return cases;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getDay(){
		return day;
	}

	public void setDeaths(Deaths deaths){
		this.deaths = deaths;
	}

	public Deaths getDeaths(){
		return deaths;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"country = '" + country + '\'' + 
			",cases = '" + cases + '\'' + 
			",time = '" + time + '\'' + 
			",day = '" + day + '\'' + 
			",deaths = '" + deaths + '\'' + 
			"}";
		}
}
