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
package org.heliosphere.drake.base.message.manager;

import org.heliosphere.drake.base.exception.AbstractCheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Exception thrown when an error occurs while manipulating the
 * message manager.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class MessageManagerException extends AbstractCheckedException
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 */
	public MessageManagerException()
	{
		super();
	}

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 * <p>
	 * @param value Enumerated exception value.
	 */
	public MessageManagerException(final Enum<? extends IBundle> value)
	{
		super(value);
	}

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 * <p>
	 * @param key Exception key (enumerated value coming from an
	 * enumeration implementing the {@link IExceptionType} interface).
	 * @param parameters List of parameters used to populate the exception
	 * message.
	 */
	public MessageManagerException(final Enum<?> key, final Object... parameters)
	{
		super(key, parameters);
	}

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 * <p>
	 * @param exception Parent {@link Exception}.
	 */
	public MessageManagerException(final Exception exception)
	{
		super(exception);
	}

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 */
	public MessageManagerException(final String message)
	{
		super(message);
	}

	/**
	 * Exception thrown when an error occurs while manipulating the
	 * {@link IMessageManager}.
	 * <p>
	 * @param message Message describing the error being the cause of the raised
	 * exception.
	 * @param exception Parent {@link Exception}.
	 */
	public MessageManagerException(final String message, final Exception exception)
	{
		super(message, exception);
	}
}