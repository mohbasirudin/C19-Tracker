package com.bismillah.basirudin.covidapp.model.covid_03.provinsi;

import com.google.gson.annotations.SerializedName;
public class Attributes{

	@SerializedName("FID")
	private int fID;

	@SerializedName("Kode_Provi")
	private int kodeProvi;

	@SerializedName("Kasus_Meni")
	private int kasusMeni;

	@SerializedName("Kasus_Posi")
	private int kasusPosi;

	@SerializedName("Provinsi")
	private String provinsi;

	@SerializedName("Kasus_Semb")
	private int kasusSemb;

	public Attributes() {
	}

	public Attributes(int fID, int kodeProvi, int kasusMeni, int kasusPosi, String provinsi, int kasusSemb) {
		this.fID = fID;
		this.kodeProvi = kodeProvi;
		this.kasusMeni = kasusMeni;
		this.kasusPosi = kasusPosi;
		this.provinsi = provinsi;
		this.kasusSemb = kasusSemb;
	}

	public void setFID(int fID){
		this.fID = fID;
	}

	public int getFID(){
		return fID;
	}

	public void setKodeProvi(int kodeProvi){
		this.kodeProvi = kodeProvi;
	}

	public int getKodeProvi(){
		return kodeProvi;
	}

	public void setKasusMeni(int kasusMeni){
		this.kasusMeni = kasusMeni;
	}

	public int getKasusMeni(){
		return kasusMeni;
	}

	public void setKasusPosi(int kasusPosi){
		this.kasusPosi = kasusPosi;
	}

	public int getKasusPosi(){
		return kasusPosi;
	}

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setKasusSemb(int kasusSemb){
		this.kasusSemb = kasusSemb;
	}

	public int getKasusSemb(){
		return kasusSemb;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"fID = '" + fID + '\'' + 
			",kode_Provi = '" + kodeProvi + '\'' + 
			",kasus_Meni = '" + kasusMeni + '\'' + 
			",kasus_Posi = '" + kasusPosi + '\'' + 
			",provinsi = '" + provinsi + '\'' + 
			",kasus_Semb = '" + kasusSemb + '\'' + 
			"}";
		}
}