package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

/**
 * 
 * The interface DataService helps import the data on the client side with the AsyncCallback.
 * 
 * @author Lina Witzel
 * 
 * @history 23-11-2016 LW Set Up
 * 			
 * @version 23-11-2016 Version 1
 * 
 * @responsabilities This class extends the RemoteService because of usage of RPC.
 *
 */

public interface DataServiceAsync {
	void getData(AsyncCallback<ArrayList<DataPoint>> callback);
	void getMapData(int year, double minTemperature, double maxTemperature, double uncertainity,
			String city, String country,AsyncCallback<ArrayList<DataPoint>> callback);
	void getTableData(int year,double minTemperature, double maxTemperature,double uncertainty,String city, String country,AsyncCallback<ArrayList<DataPoint>> callback);
}
