package com.google.gwt.sample.climatechangeapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
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

public class TableView extends Composite{
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private FlexTable dataFlexTable = new FlexTable();
	//private String[] data = {"01-08-2000","20.00","0.10", "New York", "United States of America", "44.99N", "74.56"};
	private ReadCSVServiceAsync readSvc = (ReadCSVServiceAsync) GWT.create(ReadCSVService.class);
	
	public TableView() {
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
		// Initialize service proxy
		if (readSvc == null){
			readSvc = GWT.create(ReadCSVService.class);
		}
		
		addData();
		
		/*
		// Set up callback object
		AsyncCallback<Data[]> callback = new AsyncCallback<Data[]>(){
			public void onFailure(Throwable caught){
						
			}
			public void onSuccess(Data[] data){
				addData(data);		
			}
		};
			
		
		readSvc.readData("GlobalLandTemperaturesByMajorCity_v1.csv", callback);
		*/
	}
	
	/**
	 * 
	 * Adds the data to the table (currently not imported data).
	 * @pre		dataFlexTable is instance of FlexTable, size of data >= 7
	 * @post	i=7
	 * 
	 */
	
	public void addData(){
		readSvc.readData("GlobalLandTemperaturesByMajorCity_v1.csv", new AsyncCallback<Data[]>(){
			public void onFailure(Throwable caught) {
	              
	        }

	        public void onSuccess(Data[] data) {
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
	       	}
	   });
	}
	/*private void addData(Data[] data){
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
