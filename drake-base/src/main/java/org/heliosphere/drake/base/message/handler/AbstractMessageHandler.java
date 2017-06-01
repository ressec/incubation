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
package org.heliosphere.drake.base.message.handler;

import org.heliosphere.drake.base.application.IApplication;

/**
 * Abstract message handler.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class AbstractMessageHandler
{
	/**
	 * Application.
	 */
	private IApplication application;

	/**
	 * Creates a new abstract message handler.
	 * <p>
	 * @param application {@link IApplication}.
	 */
	public AbstractMessageHandler(final IApplication application)
	{
		this.application = application;
	}

	/**
	 * Returns the application.
	 * <p>
	 * @return {@link IApplication}.
	 */
	public final IApplication getApplication()
	{
		return application;
	}
}
