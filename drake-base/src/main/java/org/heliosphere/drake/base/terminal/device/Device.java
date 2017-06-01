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
package org.heliosphere.drake.base.terminal.device;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * A device.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class Device
{
	/**
	 * Creates a new device.
	 */
	private Device()
	{
	}

	/**
	 * Creates a default {@link IDevice}.
	 */
	private static IDevice DEFAULT =
			(System.console() == null) ? getStreamDevice(System.in, System.out) : new ConsoleDevice(System.console());

	/**
	 * Returns the default system text I/O device.
	 * <p>
	 * @return Default {@link IDevice}.
	 */
	public static IDevice getDefaultDevice()
	{
		return DEFAULT;
	}

	/**
	 * Returns a text I/O device wrapping the given streams. The default system
	 * encoding is used to decode/encode data.
	 * <p>
	 * @param in Input source.
	 * @param out Output target.
	 * @return A newly created {@link IDevice}.
	 */
	public static IDevice getStreamDevice(final InputStream in, final OutputStream out)
	{
		return new CharacterDevice(new BufferedReader(new InputStreamReader(in)), new PrintWriter(out, true));
	}

	/**
	 * Returns a character devicee wrapping the given streams.
	 * <p>
	 * @param reader Input source.
	 * @param writer Output target.
	 * @return A newly created {@link IDevice}.
	 */
	public static IDevice getCharacterDevice(final BufferedReader reader, final PrintWriter writer)
	{
		return new CharacterDevice(reader, writer);
	}
}
