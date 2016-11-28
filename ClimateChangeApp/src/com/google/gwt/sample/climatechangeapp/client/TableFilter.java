package com.google.gwt.sample.climatechangeapp.client;

import java.util.Date;
/**
 * 
 * The class TableFilter filters data.
 * 
 * @author Lucien Lotmar
 * @history 28-11-2016 LL First version
 * @version 28-11-2016 LL Current version
 * @responsabilities This class filters data for the TableView class.
 *
 */

public class TableFilter {
	private Data[] data = new Data[100];
	
	//constructor
	public TableFilter(Data[] theData){
		data=theData;
	}
	
	//updates data
	public void update(Data[] updateData){
		data=updateData;
	}
	
	//filter by country
	public Data[] filterByCountry(String country){
		for (Data entry : data) {
			if(entry!=null){
				if(country.equals(entry.getCountry())){
					entry = null;
				}
			}
		}
		return data;
	}
	
	//filter by date
	public Data[] filterByDate(Date date){
		
		return data;
	}
	
	//filter by average temperature
	public Data[] filterByAveTemp(double averageTemp){
		
		return data;
	}
	
	//filter by city
	public Data[] filterByCity(String city){
		
		return data;
	}
}