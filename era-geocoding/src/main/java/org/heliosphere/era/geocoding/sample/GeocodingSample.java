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
package org.heliosphere.era.geocoding.sample;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class GeocodingSample
{
	/**
	 * Google Maps API key.
	 */
	@SuppressWarnings("nls")
	private final static String GOOGLE_API_KEY = "AIzaSyDJsE-0pbKHV2NU1golO1VVZz5OZBnInTk";

	@SuppressWarnings("nls")
	public static void main(String[] args)
	{
		GeocodingResult[] results;

		GeoApiContext context = new GeoApiContext.Builder().apiKey(GOOGLE_API_KEY).build();

		try
		{
			results = GeocodingApi.geocode(context, "11 rue docteur coquand 74100 annemasse france").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].addressComponents));
		}
		catch (ApiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
