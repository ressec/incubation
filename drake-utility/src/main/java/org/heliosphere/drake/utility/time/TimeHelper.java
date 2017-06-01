/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.utility.time;

/**
 * A helper class for time manipulation.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class TimeHelper
{
	/**
	 * Billion constant.
	 */
	private final static long BILLION = 1000000000;

	/**
	 * Million constant.
	 */
	private final static long MILLION = 1000000;

	/**
	 * Thousand constant.
	 */
	private final static long THOUSAND = 1000;

	/**
	 * Creates a new time helper.
	 */
	private TimeHelper()
	{
	}

	/**
	 * Converts a time value expressed in nano-seconds to a time value expressed
	 * in seconds.
	 * <p>
	 * @param nano Time expressed in nano-seconds.
	 * @return Time expressed in seconds.
	 */
	public final static double nano2Second(final long nano)
	{
		return (double) nano / BILLION;
	}

	/**
	 * Converts a time value expressed in nano-seconds to a time value expressed
	 * in milli-seconds.
	 * <p>
	 * @param nano Time expressed in nano-seconds.
	 * @return Time expressed in milli-seconds.
	 */
	public final static double nano2Milli(final long nano)
	{
		return (double) nano / MILLION;
	}

	/**
	 * Converts a time value expressed in milli-seconds to a time value
	 * expressed
	 * in seconds.
	 * <p>
	 * @param milli Time expressed in milli-seconds.
	 * @return Time expressed in seconds.
	 */
	public final static double milli2Second(final long milli)
	{
		return (double) milli / THOUSAND;
	}

	/**
	 * Converts a time value expressed in nano-seconds to a Hour Minute Second
	 * string representation.
	 * <p>
	 * @param nanoSeconds Time expressed in nano-seconds.
	 * @return Time expressed in Hour-Minute-Second form.
	 */
	@SuppressWarnings("nls")
	public final static String format(final long nanoSeconds)
	{
		int hours, minutes, remainder, totalSecondsNoFraction;
		double totalSeconds, seconds;

		// Calculating hours, minutes and seconds.
		totalSeconds = nanoSeconds / 1000000000.0;
		final String s = Double.toString(totalSeconds);
		final String[] arr = s.split("\\.");
		totalSecondsNoFraction = Integer.parseInt(arr[0]);
		hours = totalSecondsNoFraction / 3600;
		remainder = totalSecondsNoFraction % 3600;
		minutes = remainder / 60;
		seconds = remainder % 60;
		if (arr[1].contains("E"))
		{
			seconds = Double.parseDouble("." + arr[1]);
		}
		else
		{
			seconds += Double.parseDouble("." + arr[1]);
		}

		// Format the string that contains hours, minutes and seconds.
		final StringBuilder result = new StringBuilder(".");
		String sep = "", nextSep = " and ";

		if (seconds > 0)
		{
			result.insert(0, " seconds").insert(0, seconds);
			sep = nextSep;
			nextSep = ", ";
		}

		if (minutes > 0)
		{
			if (minutes > 1)
			{
				result.insert(0, sep).insert(0, " minutes").insert(0, minutes);
			}
			else
			{
				result.insert(0, sep).insert(0, " minute").insert(0, minutes);
			}
			sep = nextSep;
			nextSep = ", ";
		}

		if (hours > 0)
		{
			if (hours > 1)
			{
				result.insert(0, sep).insert(0, " hours").insert(0, hours);
			}
			else
			{
				result.insert(0, sep).insert(0, " hour").insert(0, hours);
			}
		}

		return result.toString();
	}
}
