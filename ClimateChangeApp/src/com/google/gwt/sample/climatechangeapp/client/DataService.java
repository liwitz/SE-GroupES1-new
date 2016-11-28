package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService{
	ArrayList<DataPoint> getData();
	ArrayList<DataPoint> getMapData(int year,double minTemperature, double maxTemperature, double uncertainty, String city, String country);
	ArrayList<DataPoint> getTableData(int year, double minTemperature, double maxTemperature,double uncertainty, String city, String country);
	//ArrayList<DataPoint> getCountryList();
	//ArrayList<DataPoint> getCityList();
}
