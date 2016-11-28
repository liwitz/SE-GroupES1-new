package com.google.gwt.sample.climatechangeapp.client;

import java.util.ArrayList;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;
import com.google.gwt.sample.climatechangeapp.shared.DataPoint;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
	
/**
 * 
 * The class WorldMapView generates the world map and points on the map.
 * 
 * @author Carla Coccia
 * 
 * @history 10-11-2016 CC First version
 * 			10-11-2016 LL Initialization 
 * 			11-11-2016 LL Color of markers
 * 			12-11-2016 CC Initialization corrected
 * 			26-11-2016 LW Adjustments for data import
 * 
 * @version 12-11-2016 CC Version 1
 * 			28-11-2016 LW Version 2
 *
 * @responsabilities This class initializes the world map, draws the map, adds data and draws markers.
 *
 */
	
public class WorldMapView extends DataView{

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;
	private int currentYear=2013;
	private double maxTemperature=40;
	private double minTemperature=-30;
	private double uncertainty=15;
	
	//All the cities and countries will be shown
	private String city="city";
	private String country="country";

	// Creates the map view
	public WorldMapView() {
		initWidget(mainPanel);
		initialize();
	}

	/**
	 * 
	 * Initializes the map, runs and draws it.
	 * @pre		geoChart is instantiated, mainPanel is instantiated
	 * @post	map is loaded and drawn
	 * 
	 */
		
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				mainPanel.add(geoChart);
				draw();
			}
		});
	}

	/**
	 * 
	 * Draws the map and markers.
	 * @pre		geoChart is instantiated, mainPanel is instantiated
	 * @post	map is loaded and drawn
	 * 
	 */
		
	private void draw() {
		// Prepare the data 
		// TODO: Function, which gets the right data form the list.

		if (getData() != null) {

			DataTable dataTable = DataTable.create();
			dataTable.addColumn(ColumnType.STRING, "City");
			dataTable.addColumn(ColumnType.NUMBER, "Temperature");
			dataTable.addColumn(ColumnType.NUMBER,"Uncertainty");

			dataTable.addRows(getData().size());
			
			for (int i = 0; i < getData().size(); i++) {
				dataTable.setValue(i, 0, getData().get(i).getRegion());
				dataTable.setValue(i, 1, getData().get(i).getAverageTemperature());
				dataTable.setValue(i, 2, getData().get(i).getUncertainty());
			}
			
			// Set options for GeoChart
			GeoChartOptions options = GeoChartOptions.create();
			options.setDisplayMode(DisplayMode.MARKERS);
			GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
			geoChartColorAxis.setColors(getNativeArray());
			options.setColorAxis(geoChartColorAxis);
			options.setDatalessRegionColor("gray");
			geoChart.draw(dataTable, options);
		}
	}

	/**
	 * 
	 * Gets the data for the world map considering certain criteria/attributes.
	 * @pre		-
	 * @post	-
	 * 
	 */
	
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				draw();
			}

			@Override
			public void onFailure(Throwable caught) {
				
			}
		};

		// Call to server with the correct attributes (default at the beginning)
		getDataService().getMapData(currentYear,minTemperature,maxTemperature,uncertainty,city,country,callback);
	}
	
	/**
	 * 
	 * Returns the colors for the markers.
	 * @pre		-
	 * @post	-
	 * @return Array with colors for markers.
	 * 
	 */
	
	private native JsArrayString getNativeArray() /*-{
		return [ "0000FF", "5858FA", "A9A9F5", "F7819F", "FE2E64", "FF0040" ];
	}-*/;
	
}
