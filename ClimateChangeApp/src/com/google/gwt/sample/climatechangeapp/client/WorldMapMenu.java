package com.google.gwt.sample.climatechangeapp.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
/**import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopHandler;
*/

/**
 * 
 * The class WorldMapView generates the world map and points on the map.
 * 
 * @author Carla Coccia
 * 
 * @history 26-11-2016 CC Set Up for filter
 * 			26-11-2016 LL Styling list boxes
 * 			
 * @version 28-11-2016 CC Version 1
 *
 * @responsabilities This class initializes the world map, draws the map, adds data and draws markers.
 *
 */

public class WorldMapMenu extends VerticalPanel {
	
	private VerticalPanel sliderPanel= new VerticalPanel();
	private VerticalPanel listPanel= new VerticalPanel();
	private VerticalPanel countryPanel = new VerticalPanel();
	private VerticalPanel cityPanel = new VerticalPanel();
	private VerticalPanel yearPanel = new VerticalPanel();
	private VerticalPanel tempPanel = new VerticalPanel();
	private HorizontalPanel uncertaintyPanel = new HorizontalPanel();
	private VerticalPanel uncertaintySliderPanel= new VerticalPanel();
	
	//Creates list boxes and their labels
	private ListBox chooseCountry= new ListBox();
	private Label countryLabel= new Label("Country");
	private ListBox chooseCity= new ListBox();
	private Label cityLabel= new Label("City");
	 
	private Label yearLabel= new Label("Year");

	private Label tempLabel= new Label("Temperature");
	
	private Label tempUncertaintyLabel= new Label("Uncertainty");
	private ToggleButton hideUncertainty = new ToggleButton("Hide uncertain Data");
	
	public WorldMapMenu(){
		super();
		fillPanels();
		sliderPanel.setSpacing(8);
		sliderPanel.setHeight("60%");
		listPanel.setSpacing(8);
		listPanel.setHeight("40%");
		add(sliderPanel);
		add(listPanel);
	}
	
/**	private void handleEvents(){       DOES NOT WORK!
		
		// Action when Country is chosen
		chooseCountry.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent changeEvent) {
				// TODO show data on map (use getSelectedIndex/Value Method)
			}
		});
				
		// Action when City is chosen
		chooseCity.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent changeEvent) {
				// TODO show data on map (use getSelectedIndex/Value Method)
			}
		});	
		
		
		//Action when year is changed
		yearSlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
		//Action when temp is changed
		tempSlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
		
		//Action when tempUncertainty is changed
		tempUncertaintySlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
	}
	*/

	/**
	 * 
	 * Fills panels will filter options.
	 * @pre		-
	 * @post	-
	 * 
	 */
	
	private void fillPanels(){
		
		//Add everything in slidePanel
		yearPanel.add(yearLabel);
		tempPanel.add(tempLabel);
		uncertaintySliderPanel.add(tempUncertaintyLabel);
		uncertaintyPanel.add(uncertaintySliderPanel);
		uncertaintyPanel.add(hideUncertainty);
		sliderPanel.add(yearPanel);
		sliderPanel.add(tempPanel);
		sliderPanel.add(uncertaintyPanel);
		styleListBox();
		
		//Adds everything in listPanel
		countryPanel.add(countryLabel);
		countryPanel.add(chooseCountry);
		cityPanel.add(cityLabel);
		cityPanel.add(chooseCity);
		listPanel.add(countryPanel);
		listPanel.add(cityPanel);
		
	
	}
	
	/**
	 * 
	 * Adds the items to choose for filtering.
	 * @pre		-
	 * @post	-
	 * 
	 */
	
	private void styleListBox(){
		
		chooseCity.setVisibleItemCount(1);
		chooseCity.addItem("Abidjan");
		chooseCity.addItem("Addis Abeba");
		chooseCity.addItem("Ahmadabad");
		chooseCity.addItem("Aleppo");
		
		
		chooseCountry.setVisibleItemCount(1);
		chooseCountry.addItem("Côte d'Ivoire");
		chooseCountry.addItem("Ethiopia");
		chooseCountry.addItem("India");
		chooseCountry.addItem("Syria");
	}
	
/*	private void styleSlider() {          DOES NOT WORK!
		// TODO Anpassung der Mindest- und Maximaltemperatur.
		yearSlider.setMin(1849);
		yearSlider.setMax(2013);
		yearSlider.setWidth("400px");
		tempSlider.setMin(-5);
		tempSlider.setMax(50);
		tempSlider.setWidth("400px");
		tempUncertaintySlider.setMin(0);
		tempUncertaintySlider.setMax(3);
		tempUncertaintySlider.setWidth("100px");
	}
*/

}
