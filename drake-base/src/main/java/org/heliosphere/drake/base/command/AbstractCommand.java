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

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.type.CommandCategoryType;
import org.heliosphere.drake.base.terminal.device.IDevice;

/**
 * Abstract implementation of a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractCommand
{
	/**
	 * Command authorized prefixes.
	 */
	@SuppressWarnings("nls")
	protected final String PREFIXES = "#*/.;:?&%";

	/**
	 * Default command category.
	 */
	protected final CommandCategoryType category = CommandCategoryType.Normal;

	/**
	 * Application being the owner of the command.
	 */
	private IApplication application = null;

	/**
	 * Creates a new command given a owner application.
	 * <p>
	 * @param application {@link IApplication} being the owner of the command.
	 */
	@SuppressWarnings("nls")
	public AbstractCommand(final IApplication application)
	{
		Validate.notNull(application, "Application cannot be null");

		this.application = application;
	}

	/**
	 * Returns the parent application.
	 * <p>
	 * @return {@link IApplication} being the owner application.
	 */
	public final IApplication getApplication()
	{
		return application;
	}

	/**
	 * Returns the console of the terminal.
	 * <p>
	 * @return {@link IDevice} being the console associated to the terminal.
	 */
	public final IDevice getDevice()
	{
		return getApplication().getTerminal().getDevice();
	}

	/**
	 * Returns the command category.
	 * <p>
	 * @return {@link CommandCategoryType}.
	 */
	public final CommandCategoryType getCategory()
	{
		return category;
	}
}
