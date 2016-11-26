package com.google.gwt.sample.climatechangeapp.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("read")
public interface ReadCSVService {
	Data[] readData(String filename);
	Data getData(String[] split);
	double convertDouble(String s);
	Date convertDate(String s);
}
