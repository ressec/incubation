/*
 * Copyright(c) 2010-2012 Heliosphere.
 * ---------------------------------------------------------------------------
 * This file is part of the RELIC foundation project which is licensed
 * under the Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources ; if not visit:
 * http://www.apache.org/licenses/LICENSE-2.0.html
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.server;

import org.heliosphere.drake.base.exception.AbstractUncheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * An unchecked exception thrown when an error occured when initializing a
 * server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
public class ServerInitializationException extends AbstractUncheckedException
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown when an error occured while initializing a server.
	 */
	public ServerInitializationException()
	{
		super();
	}

	/**
	 * Exception thrown when an error occured while initializing a server.
	 * <p>
	 * @param key Resource bundle key (enumerated value coming from an
	 * enumeration implementing the {@link IBundle} interface).
	 */
	public ServerInitializationException(final Enum<? extends IBundle> key)
	{
		super(key);
	}

	/**
	 * Exception thrown when an error occured while initializing a server.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public ServerInitializationException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception thrown when an error occured while initializing a server.
	 * <p>
	 * @param exception Parent {@link Exception}.
	 */
	public ServerInitializationException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception thrown when an error occured while initializing a server.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public ServerInitializationException(final String message)
	{
		super(message);
	}

	/**
	 * Exception thrown when an error occured while initializing a server.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent {@link Exception}.
	 */
	public ServerInitializationException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}