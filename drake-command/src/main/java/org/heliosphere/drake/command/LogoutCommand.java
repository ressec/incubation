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

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.client.IClient;

/**
 * Logout command.
 * <p>
 * This command is used to disconnect a connected user from a server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class LogoutCommand extends AbstractCommand
{
	/**
	 * Constructs a new logout command.
	 * <p>
	 * @param application Parent application.
	 */
	public LogoutCommand(final IApplication application)
	{
		super(application);
	}

	/**
	 * Logout a connected user from a server.
	 */
	@SuppressWarnings("nls")
	@Command(name = "logout", description = "Logout from a server", version = "1.0", author = "Heliosphere", aliases = { "disconnect" })
	public final void logout()
	{
		IClient client = (IClient) getApplication();

		if (!client.getPlayer().isConnected())
		{
			final String message = "User not connected";

			log.error(message);
			getApplication().getTerminal().getDevice().printf("%s", message);
		}
		else
		{
			client.getPlayer().disconnect();
		}
	}
}
