package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

public interface DataServiceAsync {
	void getData(AsyncCallback<ArrayList<DataPoint>> callback);
	void getMapData(int year, double minTemperature, double maxTemperature, double uncertainity,
			String city, String country,AsyncCallback<ArrayList<DataPoint>> callback);
	void getTableData(int year,double minTemperature, double maxTemperature,double uncertainty,String city, String country,AsyncCallback<ArrayList<DataPoint>> callback);
	//void getCountryList(AsyncCallback<ArrayList<DataPoint>> callback);
	//void getCityList(AsyncCallback<ArrayList<DataPoint>> callback);
}
