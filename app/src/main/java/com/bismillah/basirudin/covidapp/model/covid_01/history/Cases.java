package com.bismillah.basirudin.covidapp.model.covid_01.history;

public class Cases{
	private String jsonMemberNew;
	private int recovered;
	private int total;
	private int critical;
	private int active;

	public void setJsonMemberNew(String jsonMemberNew){
		this.jsonMemberNew = jsonMemberNew;
	}

	public String getJsonMemberNew(){
		return jsonMemberNew;
	}

	public void setRecovered(int recovered){
		this.recovered = recovered;
	}

	public int getRecovered(){
		return recovered;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setCritical(int critical){
		this.critical = critical;
	}

	public int getCritical(){
		return critical;
	}

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	@Override
 	public String toString(){
		return 
			"Cases{" + 
			"new = '" + jsonMemberNew + '\'' + 
			",recovered = '" + recovered + '\'' + 
			",total = '" + total + '\'' + 
			",critical = '" + critical + '\'' + 
			",active = '" + active + '\'' + 
			"}";
		}
}
