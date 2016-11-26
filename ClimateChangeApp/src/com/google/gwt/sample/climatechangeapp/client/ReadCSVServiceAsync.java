package com.google.gwt.sample.climatechangeapp.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReadCSVServiceAsync {
	void readData(String filename, AsyncCallback<Data[]> callback);
	void getData(String[] split, AsyncCallback<Data> callback);
	void convertDouble(String s, AsyncCallback<Double> callback);
	void convertDate(String s, AsyncCallback<Date> callback);
}
