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
package org.heliosphere.drake.base.player;

import org.heliosphere.drake.base.exception.AbstractCheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Exception used when an error occured while manipulating a player.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class PlayerException extends AbstractCheckedException
{
	/**
	 * Default serialization identifer.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception raised when an error occured with a player.
	 */
	public PlayerException()
	{
		super();
	}

	/**
	 * Exception raised when an error occured with a player.
	 * <p>
	 * @param type Exception type.
	 */
	public PlayerException(final Enum<? extends IBundle> type)
	{
		super(type);
	}

	/**
	 * Exception raised when an error occured with a player.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public PlayerException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception raised when an error occured with a player.
	 * <p>
	 * @param exception Parent exception.
	 */
	public PlayerException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception raised when an error occured with a player.
	 * <p>
	 * @param message Exception message.
	 */
	public PlayerException(final String message)
	{
		super(message);
	}

	/**
	 * Exception raised when an error occured with a player.
	 * <p>
	 * @param message Exception message.
	 * @param exception Parent exception.
	 */
	public PlayerException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}
