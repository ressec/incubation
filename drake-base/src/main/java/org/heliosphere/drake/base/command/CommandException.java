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
package org.heliosphere.drake.base.command;

import org.heliosphere.drake.base.exception.AbstractCheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * A checked exception used when an error occured while manipulating a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class CommandException extends AbstractCheckedException
{
	/**
	 * Default serialization identifer.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception raised when an error occured with a command.
	 */
	public CommandException()
	{
		super();
	}

	/**
	 * Exception raised when an error occured with a command.
	 * <p>
	 * @param type Exception type.
	 */
	public CommandException(final Enum<? extends IBundle> type)
	{
		super(type);
	}

	/**
	 * Exception raised when an error occured with a command.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public CommandException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception raised when an error occured with a command.
	 * <p>
	 * @param exception Parent exception.
	 */
	public CommandException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception raised when an error occured with a command.
	 * <p>
	 * @param message Exception message.
	 */
	public CommandException(final String message)
	{
		super(message);
	}

	/**
	 * Exception raised when an error occured with a command.
	 * <p>
	 * @param message Exception message.
	 * @param exception Parent exception.
	 */
	public CommandException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}
