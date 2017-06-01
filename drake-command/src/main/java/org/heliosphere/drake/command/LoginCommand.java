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

import java.io.IOException;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.base.command.annotation.Parameter;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;
import org.heliosphere.drake.client.IClient;
import org.heliosphere.drake.client.resource.bundle.BundleClient;
import org.heliosphere.drake.client.type.ClientPropertiesType;

/**
 * Login command.
 * <p>
 * This command is used to connect a client application (its associated user) to
 * a server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class LoginCommand extends AbstractCommand
{
	/**
	 * Constructs a new login command.
	 * <p>
	 * @param application Parent application.
	 */
	public LoginCommand(final IApplication application)
	{
		super(application);
	}

	/**
	 * Login command used to connect a client application to a server.
	 * <p>
	 * @param identifier User identifier.
	 * @param password User password.
	 */
	@SuppressWarnings("nls")
	@Command(name = "login", description = "Login to a server", version = "1.0", author = "Heliosphere", aliases = { "connect" })
	public final void login(final @Parameter(required = true) String identifier, final @Parameter(required = true) String password)
	{
		IClient client = (IClient) getApplication();

		if (client.getPlayer().isConnected())
		{
			final String message = ResourceBundleManager.getMessage(
					BundleClient.UserAlreadyConnected,
					client.getPlayer().getName(),
					client.getProperty(ClientPropertiesType.ServerHostName),
					client.getProperty(ClientPropertiesType.ServerPortNumber));

			log.warn(message);
			getApplication().getTerminal().getDevice().printf("%s", message);
		}
		else
		{
			try
			{
				client.getPlayer().connect(identifier, password);
			}
			catch (final IOException ioe)
			{
				final String message = ResourceBundleManager.getMessage(
						BundleClient.UserLoginFailed,
						identifier,
						client.getProperty(ClientPropertiesType.ServerHostName),
						client.getProperty(ClientPropertiesType.ServerPortNumber),
						ioe.getLocalizedMessage());

				log.error(message, ioe);
				getApplication().getTerminal().getDevice().printf("%s", message);
			}
		}
	}
}
