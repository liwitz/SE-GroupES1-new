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
	
	/**
	 * 
	 * Puts a new dataset in the variable data.
	 * @pre		new dataset array isn't longer then the length of data
	 * @post	variable data is passed dataset
	 * 
	 */
	public void update(Data[] updateData){
		data=updateData;
	}
	
	/**
	 * 
	 * Checks each entry in data and sets the ones where their country entry doesn't match the past along string to null
	 * @pre		a string is past
	 * @post	only entries remain where their country variable matches the past string
	 * 
	 */
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
	
	/**
	 * 
	 * filter by date
	 * @pre		
	 * @post	
	 * no @pre and @post yet because method nod written yet, data set must be imported first
	 * may need to be split up in filter by year / month / day
	 */
	public Data[] filterByDate(Date date){
		
		return data;
	}
	
	/**
	 * 
	 * filter by average temperature
	 * @pre		
	 * @post	
	 * no @pre and @post yet because method nod written yet, data set must be imported first
	 */
	public Data[] filterByAveTemp(double averageTemp){
		
		return data;
	}
	
	/**
	 * 
	 * filter by city
	 * @pre		
	 * @post	
	 * no @pre and @post yet because method nod written yet, data set must be imported first
	 */
	public Data[] filterByCity(String city){
		
		return data;
	}
}