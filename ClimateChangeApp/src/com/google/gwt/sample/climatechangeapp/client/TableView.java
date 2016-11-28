package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.climatechangeapp.shared.DataPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * The class TableView generates the table and fills in the data.
 * 
 * @author Lina Witzel
 * @history 08-11-2016 LW First version
 * 			09-11-2016 LL Constructor corrected
 * 			12-11-2016 LW Insertion test data
 * 			13-11-2016 LW Change test data
 * 			26-11-2016 LW Add AsyncCallback and data import
 * @version 13-11-2016 LW Version 1
 * @responsabilities This class inititalizes the table and adds the data.
 *
 */

public class TableView extends DataView{
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private FlexTable dataFlexTable = new FlexTable();
	private int currentYear=2013;
	private double uncertainty=15;
	private double minTemperature = -30;
	private double maxTemperature = 40;
	private String country = "country";
	private String city ="city";
	ScrollPanel scroll;
	private List<DataPoint> Countries = new ArrayList<>();
	private List<DataPoint> Cities = new ArrayList<>();
	
	public List<DataPoint> getCountries() {
		return Countries;
	}
	public void setCountries(List<DataPoint> countries) {
		Countries = countries;
	}
	
	public List<DataPoint> getCities() {
		return Cities;
	}
	public void setCities(List<DataPoint> cities) {
		Cities = cities;
	}
	
	public TableView() {
		fetchData();
		initialize();
		initWidget(mainPanel);
	}
	
	/**
	 * 
	 * Initializes the table, setting the header, styling and adding the data.
	 * @pre		dataFlexTable is instance of FlexTable, mainPanel is instantiated
	 * @post	table is visible
	 * 
	 */
	
	private void initialize(){
		dataFlexTable.setText(0, 0, "Date");
		dataFlexTable.setText(0, 1, "Average Temperature");
		dataFlexTable.setText(0, 2, "Average Temperature Uncertainty");
		dataFlexTable.setText(0, 3, "City");
		dataFlexTable.setText(0, 4, "Country");
		dataFlexTable.setText(0, 5, "Latitude");
		dataFlexTable.setText(0, 6, "Longitude");
	
		dataFlexTable.getRowFormatter().addStyleName(0, "tableHeader");
		dataFlexTable.addStyleName("table");
		dataFlexTable.getCellFormatter().addStyleName(0, 0, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 1, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 2, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 3, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 4, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 5, "tableNumericColumn");
		dataFlexTable.getCellFormatter().addStyleName(0, 6, "tableNumericColumn");
	
		mainPanel.add(dataFlexTable);
		mainPanel.add(scroll);
		// Initialize service proxy
		
		addData();
		
	}
	
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				//initialize the Table once the data from the database are ready to avoid errors
				addData();		
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Failed");
			}
		};
		// call to server
		getDataService().getTableData(currentYear,minTemperature, maxTemperature, uncertainty,city, country, callback);
	}
	
	/**
	 * 
	 * Adds the data to the table (currently not imported data).
	 * @pre		dataFlexTable is instance of FlexTable, size of data >= 7
	 * @post	i=7
	 * 
	 */
	
	public void addData(){
		
	}
	/*public void addData(Data[] data){
		int row = dataFlexTable.getRowCount();
		for(int i=0; i<1; i++){
			for(int j=0; j<7; i++){
				if(data[i] == null){
					dataFlexTable.setText(row, j, "-");
				}
				else if(j==0){
					dataFlexTable.setText(row, j, String.valueOf(data[i].getDate()));
				}
				else if(j==1){
					dataFlexTable.setText(row, j, String.valueOf(data[i].getAverageTemp()));
				}
				else if(j==2){
					dataFlexTable.setText(row, j, String.valueOf(data[i].getAverageTempUncertainty()));
				}
				else if(j==3){
					dataFlexTable.setText(row, j, data[i].getCity());
				}
				else if(j==4){
					dataFlexTable.setText(row, j, data[i].getCountry());
				}
				else if(j==5){
					dataFlexTable.setText(row, j, String.valueOf(data[i].getLatitude()));
				}
				else if(j==6){
					dataFlexTable.setText(row, j, String.valueOf(data[i].getLongitude()));
				}
				else{
					dataFlexTable.setText(row, j, "-");
				}
			}
		}
	}*/
}
