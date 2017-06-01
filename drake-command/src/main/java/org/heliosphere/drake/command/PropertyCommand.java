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
package org.heliosphere.drake.command;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.base.command.annotation.Parameter;
import org.heliosphere.drake.client.IClient;

/**
 * Property command.
 * <p>
 * This command is used to display on the terminal the value of a given
 * property.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class PropertyCommand extends AbstractCommand
{
	/**
	 * Constructs a new property command.
	 * <p>
	 * @param application Parent application.
	 */
	public PropertyCommand(final IApplication application)
	{
		super(application);
	}

	/**
	 * Property command.
	 * <p>
	 * @param property Property key.
	 */
	@SuppressWarnings("nls")
	@Command(name = "property", description = "Prints the value of a property", version = "1.0", author = "Heliosphere", aliases = { "prop", "p" })
	public final void property(final @Parameter(required = true) String property)
	{
		IClient client = (IClient) getApplication();

		if (property == null || property.trim().isEmpty())
		{
			getDevice().printf("property cannot be null or empty!");
		}

		getDevice().printf("Value of property %s = %s", property, client.getProperty(property));
	}
}
