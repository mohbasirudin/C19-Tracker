package com.bismillah.basirudin.covidapp.model.covid_01.history;

public class Deaths{
	private String jsonMemberNew;
	private int total;

	public void setJsonMemberNew(String jsonMemberNew){
		this.jsonMemberNew = jsonMemberNew;
	}

	public String getJsonMemberNew(){
		return jsonMemberNew;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	@Override
 	public String toString(){
		return 
			"Deaths{" + 
			"new = '" + jsonMemberNew + '\'' + 
			",total = '" + total + '\'' + 
			"}";
		}
}
