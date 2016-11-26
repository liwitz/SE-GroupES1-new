package com.google.gwt.sample.climatechangeapp.client;


import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.KeyCodes;
//import com.google.gwt.event.dom.client.KeyUpEvent;
//import com.google.gwt.event.dom.client.KeyUpHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
//import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 * 
 * The class ClimateChangeApp generates the UI.
 * 
 * @author Sophy Chhong
 * @history 11-11-2016 SC Constructor & onModuleLoad
 * 			12-11-2016 SC Constructor corrected
 * @version 12-11-2016 SC Version 1
 * @responsabilities This class creates tabs including the world map and the data table.
 *
 */

public class ClimateChangeApp extends TabLayoutPanel implements EntryPoint {
	
	private WorldMapView worldMapView = new WorldMapView();
	private TableView tableView = new TableView();
	private WorldMapMenu worldMapMenu = new WorldMapMenu();
	private DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
	
	
	//private Button exportButton= new Button("Export data");
	//private Data data;
	
	public ClimateChangeApp(){
		
		super(9, Style.Unit.EM);
		//Creates TabLayoutPanel with two tabs
		
		
		add(dockLayoutPanel, "World Map");
		add(tableView, "Data Table");
		addStyleName("tabs");
		selectTab(0);

		//Add tabPanel to north
		dockLayoutPanel.addSouth(worldMapMenu,10);
		dockLayoutPanel.add(worldMapView);
		//addSouth(exportButton, 10);
	}
	
	/*
	 * private static final String SERVER_ERROR = "An error occurred while "
	 
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	 */
	
	/**
	 * 
	 * Loads app.
	 * @pre		-
	 * @post	app is loaded
	 * 
	 */
	 
	
	public void onModuleLoad() {
		ClimateChangeApp app = new ClimateChangeApp();
		RootLayoutPanel.get().add(app);
		
		//data = new Data();
		//data.getData();
	}
	
}
