package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

/**
 * 
 * The interface DataService lets us import the data on the client side
 * 
 * @author Lina Witzel
 * 
 * @history 23-11-2016 LW Set Up
 * 			
 * @version 23-11-2016 Version 1
 * 
 * @responsabilities This class extends the RemoteService because of the usage RPC.
 *
 */

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService{
	ArrayList<DataPoint> getData();
	ArrayList<DataPoint> getMapData(int year, double minTemperature, double maxTemperature, double uncertainty, String city, String country);
	ArrayList<DataPoint> getTableData(int year, double minTemperature, double maxTemperature,double uncertainty, String city, String country);
}
