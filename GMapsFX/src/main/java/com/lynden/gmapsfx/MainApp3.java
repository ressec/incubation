/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.lynden.gmapsfx;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp3 extends Application implements MapComponentInitializedListener
{

	GoogleMapView mapView;
	GoogleMap map;

	@Override
	public void start(Stage stage) throws Exception
	{

		//Create the JavaFX component and set this as a listener so we know when 
		//the map has been initialized, at which point we can then begin manipulating it.
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);

		Scene scene = new Scene(mapView);

		stage.setTitle("JavaFX and Google Maps");
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void mapInitialized()
	{
		//Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(46.19584589999999, 6.2327423))
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.zoom(12);

		map = mapView.createMap(mapOptions);

		//Add a marker to the map
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.position(new LatLong(46.19584589999999, 6.2327423))
				.visible(Boolean.TRUE)
				.title("My Marker");

		Marker marker = new Marker(markerOptions);

		map.addMarker(marker);

	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
