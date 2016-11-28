package com.google.gwt.sample.climatechangeapp.client;

import java.io.BufferedReader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.FileReader;
import java.util.Date;


import java.io.Serializable;

public class Data implements Serializable { 
	
	//dt,AverageTemperature,AverageTemperatureUncertainty,City,Country,Latitude,Longitude

	private double year;
	private double month;
	private double averageTemperature;
	private double averageTemperatureUncertainty;
	private String city;
	private String country;
	private double latitude;
	private double longitude;

	public double getYear() {
	    return year;
	}
	public void setYear(double year) {
	    this.year= year;
	}

	public double getMonth() {
	    return month;
	}
	public void setMonth(double month) {
	    this.month= month;
	}
	
	public double getAverageTemp() {
	    return averageTemperature;
	}
	
	public void setAverageTemp(double averageTemp) {
	    this.averageTemperature= averageTemp;;
	}

	public double getAverageTempUncertainty() {
	    return averageTemperatureUncertainty;
	}
	
	public void setAverageTempUncertainty(double averageTempUncertainty) {
	    this.averageTemperatureUncertainty= averageTempUncertainty;
	}

	public String getCity() {
	    return city;
	}
	
	public void setCity(String city){
		this.city= city;
	}


	public String getCountry() {
	    return country;
	}
	
	public void setCountry(String country){
		this.country= country;
	}
	public double getLatitude() {
	    return latitude;
	}

	public void setLatitude(double latitude) {
	    this.latitude= latitude;
	}
	
	public double getLongitude() {
	    return longitude;
	}

	public void setLongitude(double longitude) {
	    this.longitude= longitude;
	}
	
	//public Data [] getDataByYear(){
			// TODO auto-generated method stub
		//}
	
}