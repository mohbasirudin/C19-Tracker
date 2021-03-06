package com.bismillah.basirudin.covidapp.model.covid_01.statistic;
import com.google.gson.annotations.SerializedName;
public class Deaths{

	@SerializedName("new")
	private String jsonMemberNew;

	@SerializedName("total")
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