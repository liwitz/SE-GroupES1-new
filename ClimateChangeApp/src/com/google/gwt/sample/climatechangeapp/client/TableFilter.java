package com.google.gwt.sample.climatechangeapp.client;

import java.util.Date;
/**
 * 
 * The class TableFilter filters data.
 * 
 * @author Lucien Lotmar
 * @history 28-11-2016 LL First version
 * @version 28-11-2016 
 * @responsabilities This class filters data for the TableView class.
 *
 */

public class TableFilter {
	private Data[] data = new Data[100];
	
	public TableFilter(Data[] theData){
		data=theData;
	}
	
	public void update(Data[] updateData){
		data=updateData;
	}
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

}