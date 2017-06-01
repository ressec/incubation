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
package org.heliosphere.drake.base.terminal;

import org.heliosphere.drake.base.exception.AbstractUncheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * An unchecked exception used when an error occurs while trying to display data
 * on the terminal.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class TerminalDisplayException extends AbstractUncheckedException
{
	/**
	 * Default serialization identifer.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 */
	public TerminalDisplayException()
	{
		super();
	}

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 * <p>
	 * @param type Exception type.
	 */
	public TerminalDisplayException(final Enum<? extends IBundle> type)
	{
		super(type);
	}

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public TerminalDisplayException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 * <p>
	 * @param exception Parent exception.
	 */
	public TerminalDisplayException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 * <p>
	 * @param message Exception message.
	 */
	public TerminalDisplayException(final String message)
	{
		super(message);
	}

	/**
	 * Exception raised when an error occured while displaying data on the
	 * terminal.
	 * <p>
	 * @param message Exception message.
	 * @param exception Parent exception.
	 */
	public TerminalDisplayException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}
