package com.google.gwt.sample.climatechangeapp.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.sample.climatechangeapp.client.DataService;
import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

/**
 * 
 * The class DataServiceImpl is the service on the server-side which allows us to get the data from the database.
 * 
 * @author Lina Witzel
 * 
 * @history 23-11-2016 LW Set up and login database
 * 			23-11-2016 LW Method for data in map
 * 			24-11-2016 CC Method for data in table
 * 
 * @version 24-11-2016 LW Version 1
 * 
 * @responsabilities This class gets the data and prepares it for the table or the world map.
 *
 */

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	
	private static final String DATA_FILE = "resources/GlobalLandTemperaturesByMajorCity_v1.csv";
	private static final String CSV_SEPARATOR = ",";
	
	//Login for database
	private static final String PROD_PASSWORD = "1234";
	private static final String PROD_URL="jdbc:google:mysql://climatechangeappes1:europe-west1:es1/es1";
	private static final String PROD_USER="root";
	
	private static final String DEV_USER="paedi";
	private static final String DEV_PASSWORD="AY0nVCAmYDL331og";
	private static final String DEV_URL="jdbc:mysql://paedi.icu.uzh.ch:8080/paedi";
	
	private String testDriver = "com.mysql.jdbc.Driver";
    private String prodDriver = "com.mysql.jdbc.GoogleDriver";
    public DataServiceImpl(){}

    /**
	 * 
	 * Sets up connection to database.
	 * @pre		-
	 * @post	-
	 * @return Connection to database
	 * 
	 */
    
    public Connection getConnection(){
        
        Connection conn = null;

        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

            try {
                Class.forName(prodDriver); 
                conn = DriverManager.getConnection(PROD_URL,PROD_USER,PROD_PASSWORD );
            }catch(Exception e){
                e.printStackTrace();
            }

        } else {
        	
            try {
                Class.forName(testDriver);
                conn = DriverManager.getConnection(DEV_URL,DEV_USER,DEV_PASSWORD);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return conn;

    }
    
    /**
	 * 
	 * Gets the data from the database and returns it in form of an ArrayList.
	 * @pre		-
	 * @post	-
	 * @return ArrayList with data
	 * 
	 */
    
	public ArrayList<DataPoint> getData() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList();
		
		try {
			statement = connection.prepareStatement("SELECT * FROM `temperature-data`");
			result = statement.executeQuery();
			
			while (result.next()) {
				
				double temp = result.getDouble("Temperature");
				String city = result.getString("City");
				String country = result.getString("Country");
				DataPoint p = new DataPoint();
				p.setAverageTemperature(temp);
				p.setRegion(city);
				p.setCountry(country);

				data.add(p);		
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * Gets the data from the database for the world map by criteria (especially year) selected at world map.
	 * @pre		-
	 * @post	-
	 * @return ArrayList with filtered data for world map.
	 * 
	 */
	
	public ArrayList<DataPoint> getMapData(int year,double minTemperature, double maxTemperature,
			double uncertainty, String selectedCity, String selectedCountry) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList();
		
		String query = 
				"SELECT YEAR(Date), City, AVG(Temperature) AS t FROM `temperature-data` "
						+ "WHERE YEAR(DATE) ="+year+" "
						+ "AND Temperature >="+minTemperature+" "
						+ "AND Temperature <="+maxTemperature+" "
						+ "AND Uncertainty <=" + uncertainty+" "
						+ "AND City="+selectedCity+" "
						+ "AND Country="+selectedCountry+" "
						+ "GROUP BY City, YEAR(DATE)";
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				double temp = result.getDouble("t");
				String city = result.getString("City");
				int yearPoint = year;
				
				DataPoint p = new DataPoint();
				p.setAverageTemperature(temp);
				p.setRegion(city);
				p.setYear(year);
				
				data.add(p);	
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * Gets the data from the database for the table by criteria selected at table.
	 * @pre		-
	 * @post	-
	 * @return ArrayList with filtered data for table.
	 * 
	 */
	
	public ArrayList<DataPoint> getTableData(int year,double minTemperature, double maxTemperature,double uncertainty,String city, String country) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList<>();
		
		String query = 
				"SELECT * "
				+"FROM `temperature-data` "
				+ "WHERE Year(Date) ="+ year
				+" AND Uncertainty<="+ uncertainty
				+" AND Temperature >="+ minTemperature
				+" AND Temperature <="+maxTemperature
				+" AND City ="+city
				+" AND Country ="+country;
		
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				String tmpCountry = result.getString("Country");
				String tmpCity = result.getString("City");
				double temp = result.getDouble("Temperature");
				double unct = result.getDouble("Uncertainty");
				Date date = result.getDate("Date");
				DataPoint p = new DataPoint();
				p.setCountry(tmpCountry);
				p.setRegion(tmpCity);
				p.setAverageTemperature(temp);
				p.setUncertainty(uncertainty);
				p.setDate(date);
				
				data.add(p);		
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
