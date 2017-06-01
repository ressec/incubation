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
package org.heliosphere.drake.base.application;

import org.heliosphere.drake.base.exception.AbstractUncheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Exception thrown when an error occured while manipulating an application.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class ApplicationException extends AbstractUncheckedException
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown when an error occurs while processing an application.
	 */
	public ApplicationException()
	{
		super();
	}

	/**
	 * Exception thrown when an error occurs while processing an application.
	 * <p>
	 * @param key Resource bundle key (enumerated value coming from an
	 * enumeration implementing the {@link IBundle} interface).
	 */
	public ApplicationException(final Enum<? extends IBundle> key)
	{
		super(key);
	}

	/**
	 * Exception thrown when an error occurs while processing an application.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public ApplicationException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception thrown when an error occurs while processing an application.
	 * <p>
	 * @param exception Parent {@link Exception}.
	 */
	public ApplicationException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception thrown when an error occurs while processing an application.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public ApplicationException(final String message)
	{
		super(message);
	}

	/**
	 * Exception thrown when an error occurs while processing an application.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent {@link Exception}.
	 */
	public ApplicationException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}