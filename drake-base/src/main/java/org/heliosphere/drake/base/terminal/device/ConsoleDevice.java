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

import java.io.Console;
import java.io.PrintWriter;
import java.io.Reader;

import org.heliosphere.drake.base.terminal.TerminalDisplayException;
import org.heliosphere.drake.base.terminal.TerminalException;

/**
 * A console device.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class ConsoleDevice implements IDevice
{
	/**
	 * The underlying console.
	 */
	private final Console console;

	/**
	 * Constructs a new console device.
	 * <p>
	 * @param console The underlying console.
	 */
	public ConsoleDevice(final Console console)
	{
		this.console = console;
	}

	@SuppressWarnings("nls")
	@Override
	public IDevice printf(final String format, final Object... parameters) throws TerminalDisplayException
	{
		console.format("\n");
		console.format(format, parameters);
		console.format("\n $>");
		return this;
	}

	@Override
	public Reader reader() throws TerminalException
	{
		return console.reader();
	}

	@Override
	public String readLine() throws TerminalException
	{
		return console.readLine();
	}

	@Override
	public char[] readPassword() throws TerminalException
	{
		return console.readPassword();
	}

	@Override
	public PrintWriter writer() throws TerminalException
	{
		return console.writer();
	}
}