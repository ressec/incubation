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

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import org.heliosphere.drake.base.terminal.TerminalDisplayException;
import org.heliosphere.drake.base.terminal.TerminalException;

/**
 * Interface defining the behavior of a device.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IDevice
{
	/**
	 * Formats a given text on the device.
	 * <p>
	 * @param format The format string.
	 * @param parameters Parameters.
	 * @return Returns the device.
	 * @throws TerminalDisplayException Error thrown when an error occured while
	 * trying to display data on the terminal.
	 */
	IDevice printf(final String format, final Object... parameters) throws TerminalDisplayException;

	/**
	 * Reads a line from the device.
	 * <p>
	 * @return Returns the string.
	 * @throws TerminalException Thrown if an error occurs while reading the
	 * string from the device.
	 */
	String readLine() throws TerminalException;

	/**
	 * Reads a password from the device.
	 * <p>
	 * @return Returns an array of char containing the password.
	 * @throws TerminalException Thrown if an error occurs while reading the
	 * password from the device.
	 */
	char[] readPassword() throws TerminalException;

	/**
	 * Returns the reader associated to the device.
	 * <p>
	 * @return Returns the {@link Reader}.
	 * @throws TerminalException Thrown if an error occurs while getting the
	 * reader associated to the device.
	 */
	Reader reader() throws TerminalException;

	/**
	 * Returns the writer associated to the device.
	 * <p>
	 * @return Returns the {@link Writer}.
	 * @throws TerminalException Thrown if an error occurs while getting the
	 * writer associated to the device.
	 */
	PrintWriter writer() throws TerminalException;
}
