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
import org.heliosphere.drake.base.application.type.ApplicationExitCodeType;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.client.IClient;

/**
 * Exit command used to exit the client application.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class ExitCommand extends AbstractCommand
{
	/**
	 * Constructs a new exit command.
	 * <p>
	 * @param application Parent application.
	 */
	public ExitCommand(final IApplication application)
	{
		super(application);
	}

	/**
	 * Exit command used to exit a client application.
	 */
	@Command(name = "exit", description = "Exit the client application", version = "1.0", author = "Heliosphere", aliases = { "quit" })
	public final void exit()
	{
		IClient client = (IClient) getApplication();
		client.quit(ApplicationExitCodeType.OK);
	}
}
