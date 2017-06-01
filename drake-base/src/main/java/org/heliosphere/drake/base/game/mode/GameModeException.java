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
package org.heliosphere.drake.base.game.mode;

import org.heliosphere.drake.base.exception.AbstractCheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Exception thrown when an error occurs while processing a game mode.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class GameModeException extends AbstractCheckedException
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 */
	public GameModeException()
	{
		super();
	}

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 * <p>
	 * @param value Enumerated exception value.
	 */
	public GameModeException(final Enum<? extends IBundle> value)
	{
		super(value);
	}

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public GameModeException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 * <p>
	 * @param exception Parent {@link Exception}.
	 */
	public GameModeException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public GameModeException(final String message)
	{
		super(message);
	}

	/**
	 * Exception thrown when an error occurs while processing a game mode.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent {@link Exception}.
	 */
	public GameModeException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}