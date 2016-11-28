package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.sample.climatechangeapp.shared.DataPoint;;

/**
 * 
 * The class TableView generates the table and fills in the data.
 * 
 * @author Lina Witzel
 * 
 * @history 08-11-2016 LW First version
 * 			09-11-2016 LL Constructor corrected
 * 			12-11-2016 LW Insertion test data
 * 			13-11-2016 LW Change test data
 * 			26-11-2016 LL Adjustments for data import
 * 
 * @version 13-11-2016 LW Version 1
 * 			26-11-2016 LL Version 2
 * 
 * @responsabilities This class inititalizes the table and adds the data.
 *
 */

public class TableView extends DataView{
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private CellTable<DataPoint> dataTable = new CellTable<DataPoint>();
	ScrollPanel scrollPanel = new ScrollPanel(dataTable);
	//private String[] data = {"01-08-2000","20.00","0.10", "New York", "United States of America", "44.99N", "74.56"};
	
	public TableView() {
		fetchData();
		initialize();
		initWidget(mainPanel);
	}
	
	/**
	 * 
	 * Initializes the table, setting the header (same as in database), styling and adding the data.
	 * @pre		dataTable is instance of CellTable, mainPanel is instantiated
	 * @post	table is visible
	 * 
	 */
	
	private void initialize(){
		
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
  				return object.getCountry();
			}
        };
        
        TextColumn<DataPoint> cityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return object.getRegion();
			}
        };
        
        TextColumn<DataPoint> temperatureColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return String.valueOf(object.getAverageTemperature());
			}
        };
        
        TextColumn<DataPoint> uncertainityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return String.valueOf(object.getUncertainty());
			}
        };
        
        TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO Get the data and return it
				return object.getDate().toString();
			}
        };
       
        //Sets column name as in database
        countryColumn.setDataStoreName("Country");
        cityColumn.setDataStoreName("City");
        temperatureColumn.setDataStoreName("Temperature");
        uncertainityColumn.setDataStoreName("Uncertainty");
        dateColumn.setDataStoreName("Date");
        
        //Adds columns to the table
        dataTable.addColumn(countryColumn, "Country");
        dataTable.addColumn(cityColumn, "City");
        dataTable.addColumn(temperatureColumn, "Temperature");
        dataTable.addColumn(uncertainityColumn, "Uncertainity");
        dataTable.addColumn(dateColumn, "Date");
       
        scrollPanel.setAlwaysShowScrollBars(true);
        mainPanel.add(scrollPanel);
	}
	
	/**
	 * 
	 * Gets the data for the table .
	 * @pre		-
	 * @post	-
	 * 
	 */
	
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				initialize();		
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Failed");
			}
		};
		getDataService().getData(callback);
	}
}
