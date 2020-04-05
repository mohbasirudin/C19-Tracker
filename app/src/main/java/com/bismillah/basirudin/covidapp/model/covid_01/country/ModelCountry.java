package com.bismillah.basirudin.covidapp.model.covid_01.country;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class ModelCountry{

	@SerializedName("response")
	private List<String> response;

	@SerializedName("get")
	private String get;

	@SerializedName("parameters")
	private List<Object> parameters;

	@SerializedName("results")
	private int results;

	@SerializedName("errors")
	private List<Object> errors;

	public void setResponse(List<String> response){
		this.response = response;
	}

	public List<String> getResponse(){
		return response;
	}

	public void setGet(String get){
		this.get = get;
	}

	public String getGet(){
		return get;
	}

	public void setParameters(List<Object> parameters){
		this.parameters = parameters;
	}

	public List<Object> getParameters(){
		return parameters;
	}

	public void setResults(int results){
		this.results = results;
	}

	public int getResults(){
		return results;
	}

	public void setErrors(List<Object> errors){
		this.errors = errors;
	}

	public List<Object> getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"ModelCountry{" + 
			"response = '" + response + '\'' + 
			",get = '" + get + '\'' + 
			",parameters = '" + parameters + '\'' + 
			",results = '" + results + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}