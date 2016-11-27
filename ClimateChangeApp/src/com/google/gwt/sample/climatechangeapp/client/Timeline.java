package com.google.gwt.sample.climatechangeapp.client;

import com.google.gwt.user.client.ui.Image;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;

public class Timeline extends SliderBarHorizontal{
	
         public Timeline (int maxValue, String width) {
          setLessWidget(new Image("img/slid/less.png"));
          setMoreWidget(new Image("img/slid/more.png"));
          setScaleWidget(new Image("img/slid/scale.png"), 100);
          setMoreWidget(new Image("img/slid/more.png"));
          setDragWidget(new Image("img/slid/drag.png"));
          this.setWidth(width);
          this.setMaxValue(maxValue);        
      }
}


