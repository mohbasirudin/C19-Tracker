package com.bismillah.basirudin.covidapp.model.covid_01.error;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class ModelError{

	@SerializedName("response")
	private List<Object> response;

	@SerializedName("get")
	private String get;

	@SerializedName("parameters")
	private Parameters parameters;

	@SerializedName("results")
	private int results;

	@SerializedName("errors")
	private Errors errors;

	public void setResponse(List<Object> response){
		this.response = response;
	}

	public List<Object> getResponse(){
		return response;
	}

	public void setGet(String get){
		this.get = get;
	}

	public String getGet(){
		return get;
	}

	public void setParameters(Parameters parameters){
		this.parameters = parameters;
	}

	public Parameters getParameters(){
		return parameters;
	}

	public void setResults(int results){
		this.results = results;
	}

	public int getResults(){
		return results;
	}

	public void setErrors(Errors errors){
		this.errors = errors;
	}

	public Errors getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"ModelError{" + 
			"response = '" + response + '\'' + 
			",get = '" + get + '\'' + 
			",parameters = '" + parameters + '\'' + 
			",results = '" + results + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}