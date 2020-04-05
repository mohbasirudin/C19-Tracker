package com.bismillah.basirudin.covidapp.model.covid_03.provinsi;

import com.google.gson.annotations.SerializedName;
public class ModelProvinsi{

	@SerializedName("attributes")
	private Attributes attributes;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	@Override
 	public String toString(){
		return 
			"ModelProvinsi{" +
			"attributes = '" + attributes + '\'' + 
			"}";
		}
}