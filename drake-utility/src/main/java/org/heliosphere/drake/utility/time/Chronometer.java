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
 * A helper class for method execution measurement.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class Chronometer
{
	/**
	 * Method execution measurement starting time.
	 */
	private long start = 0L;

	/**
	 * Method execution measurement ending time.
	 */
	private long stop = 0L;

	/**
	 * Creates a new chronometer and start measuring.
	 */
	public Chronometer()
	{
		start();
	}

	/**
	 * Starts measuring execution time.
	 */
	public final void start()
	{
		start = System.nanoTime();
	}

	/**
	 * Stops measuring execution time.
	 */
	public final void stop()
	{
		stop = System.nanoTime();
	}

	/**
	 * Returns execution time expressed in nano seconds.
	 * <p>
	 * @return Execution time.
	 */
	public final long getTime()
	{
		return stop - start;
	}
}
