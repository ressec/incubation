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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayStaticGoogleMap
{
	/**
	 * Main entry point.
	 * <p>
	 * @param arguments Command line arguments. 
	 */
	@SuppressWarnings("nls")
	public static void main(String[] arguments)
	{
		JFrame test = new JFrame("Google Maps");

		try
		{
			String destinationFile = "image.jpg";
			String latitude = "46.19584589999999";
			String longitude = "6.2327423";
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
					+ latitude
					+ ","
					+ longitude
					+ "&zoom=11&size=612x612&scale=2&maptype=roadmap";

			// read the map image from Google
			// then save it to a local file: image.jpg
			//
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1)
			{
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		// create a GUI component that loads the image: image.jpg
		//
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("image.jpg").getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH));
		test.add(new JLabel(imageIcon));

		// show the GUI window
		test.setVisible(true);
		test.pack();
	}

}
