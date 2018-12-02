/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter2.base.exception;

import com.heliosphere.demeter2.base.resource.bundle.IBundle;

/**
 * Exception thrown to indicate that a service has not been implemented yet.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
public class NotImplementedException extends AbstractUncheckedException
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 */
	public NotImplementedException()
	{
		super();
	}

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 * <p>
	 * @param key Resource bundle key (enumerated value coming from an
	 * enumeration implementing the {@link IBundle} interface).
	 */
	@SuppressWarnings("hiding")
	public NotImplementedException(final Enum<? extends IBundle> key)
	{
		super(key);
	}

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 * <p>
	 * @param key Exception key (enumerated value coming from an enumeration
	 * implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	@SuppressWarnings("hiding")
	public NotImplementedException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 * <p>
	 * @param exception Parent {@link Exception}.
	 */
	public NotImplementedException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public NotImplementedException(final String message)
	{
		super(message);
	}

	/**
	 * Thrown to indicate that a service is not yet implemented.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent {@link Exception}.
	 */
	public NotImplementedException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}