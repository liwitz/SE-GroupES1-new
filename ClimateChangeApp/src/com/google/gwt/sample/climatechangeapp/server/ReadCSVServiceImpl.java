package com.google.gwt.sample.climatechangeapp.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.sample.climatechangeapp.client.Data;
import com.google.gwt.sample.climatechangeapp.client.ReadCSVService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ReadCSVServiceImpl extends RemoteServiceServlet implements ReadCSVService {

	public static final String DATE = "dt";
	public static final String AVERAGETEMPERATURE = "Average Temperature";
	public static final String AVERAGETEMPERATUREUNCERTAINTY = "Average Temperature Uncertainty";
	public static final String CITY = "City";
	public static final String COUNTRY = "Country";
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static String[] headers;
	
	@Override
	public Data[] readData(String filename) {
		BufferedReader reader = null;
		String line = null;
		List<Data> data = new ArrayList<Data>();
		String[] split = null;
		int index = 0;
		
		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				split = line.split(",");
				if (split != null && split.length > 0) {
					if (index > 0)
						data.add(getData(split));
					else {
						headers = new String[split.length];
                    	System.arraycopy(split, 0, headers, 0, split.length);
					}
				}
				index++;
			}
		}
		catch (FileNotFoundException e) {
		}
		catch (Exception e) {
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
			}
		}
		return data.toArray(new Data[0]);
	}

	@Override
	public Data getData(String[] split) {
		Data model = new Data();

		for (int i = 0; i < headers.length; i++) {
			try {
				if (headers[i] != null && headers[i].equalsIgnoreCase(DATE)) {
					model.setDate(convertDate(split[i]));
				}
				else if (headers[i] != null && headers[i].equalsIgnoreCase(AVERAGETEMPERATURE)) {
					model.setAverageTemp(convertDouble(split[i]));
				}
				else if (headers[i] != null && headers[i].equalsIgnoreCase(AVERAGETEMPERATUREUNCERTAINTY)) {
					model.setAverageTempUncertainty(convertDouble(split[i]));
				}
				else if (headers[i] != null && headers[i].equalsIgnoreCase(CITY)) {
					model.setCity(split[i]);
                }
				else if (headers[i] != null && headers[i].equalsIgnoreCase(COUNTRY)) {
					model.setCountry(split[i]);
				}
				else if (headers[i] != null && headers[i].equalsIgnoreCase(LATITUDE)) {
					model.setLatitude(convertDouble(split[i]));
				}
				else if (headers[i] != null && headers[i].equalsIgnoreCase(LONGITUDE)) {
					model.setLongitude(convertDouble(split[i]));
				}
				else {
				}
			}
			catch (Exception e) {
			}
		}
		return model;
	}

	@Override
	public double convertDouble(String s) {
		double value = 0.0;
		try {
			value = Double.parseDouble(s);
		}
		catch (Exception e) {
		}
		return value;
	}

	@Override
	public Date convertDate(String s) {
		
		Date date = null;
	    try {
	        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	        date = dateTimeFormat.parse(s);
	    } 
	    catch (Exception e)
	    {
	        // ignore
	    }
	    return date;
		
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate= null;
		try {
			startDate = df.parse(s);
		}
		catch (Exception e) {
		}
		*/
	}

}
