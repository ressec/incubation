/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.exception;

import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Exception thrown to indicate that a service has not been implemented yet.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
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