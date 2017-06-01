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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import org.heliosphere.drake.base.terminal.TerminalDisplayException;
import org.heliosphere.drake.base.terminal.TerminalException;

/**
 * A character based device.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class CharacterDevice implements IDevice
{
	/**
	 * Reader.
	 */
	private final BufferedReader reader;

	/**
	 * Writer.
	 */
	private final PrintWriter writer;

	/**
	 * Constructs a new character device.
	 * <p>
	 * @param reader The reader.
	 * @param writer The writer.
	 */
	public CharacterDevice(final BufferedReader reader, final PrintWriter writer)
	{
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public CharacterDevice printf(final String fmt, final Object... params) throws TerminalDisplayException
	{
		writer.printf(fmt, params);
		return this;
	}

	@Override
	public String readLine() throws TerminalException
	{
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			throw new IllegalStateException();
		}
	}

	@Override
	public char[] readPassword() throws TerminalException
	{
		return readLine().toCharArray();
	}

	@Override
	public Reader reader() throws TerminalException
	{
		return reader;
	}

	@Override
	public PrintWriter writer() throws TerminalException
	{
		return writer;
	}
}