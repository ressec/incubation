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

import java.util.List;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.base.command.annotation.ICommandInfo;
import org.heliosphere.drake.base.command.annotation.Parameter;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.manager.Manager;

/**
 * Help command used to prints on the terminal a list of the registered
 * commands.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class HelpCommand extends AbstractCommand
{
	/**
	 * Constructs a new help command.
	 * <p>
	 * @param application Parent application.
	 */
	public HelpCommand(final IApplication application)
	{
		super(application);
	}

	/**
	 * Help command.
	 * <p>
	 * @param prefix Command prefix.
	 */
	@SuppressWarnings("nls")
	@Command(name = "help", description = "Prints a list of the registered commands", version = "1.0", author = "Heliosphere", aliases = { "h", "aide" })
	public final void help(final @Parameter(required = true) String prefix)
	{
		ICommandManager manager = Manager.getCommandManager();

		getDevice().printf("Registered commands:\n", prefix == null ? "all" : prefix, PREFIXES);

		List<ICommandInfo> commands = manager.getCommandInfo(prefix);
		if (commands.size() == 0)
		{
			getDevice().printf("No registered command!");
		}
		int count = 1;
		for (ICommandInfo info : commands)
		{
			getDevice().printf("%3d. name= %s, description= %s, author= %s, version= %s\n", Integer.valueOf(count), info.getName(), info.getDescription(), info.getAuthor(), info.getVersion());
			count++;
		}
	}
}
