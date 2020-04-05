package com.bismillah.basirudin.covidapp.model.covid_01.history;

import java.util.List;

public class ModelHistory{
	private List<ResponseItem> response;
	private String get;
	private Parameters parameters;
	private int results;
	private List<Object> errors;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
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

	public void setErrors(List<Object> errors){
		this.errors = errors;
	}

	public List<Object> getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"ModelHistory{" + 
			"response = '" + response + '\'' + 
			",get = '" + get + '\'' + 
			",parameters = '" + parameters + '\'' + 
			",results = '" + results + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}