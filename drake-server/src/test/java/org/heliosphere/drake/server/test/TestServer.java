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
package org.heliosphere.drake.server.test;

import org.heliosphere.drake.base.application.IApplicationListener;
import org.heliosphere.drake.base.application.type.ApplicationPropertiesType;
import org.heliosphere.drake.base.application.type.ApplicationRunType;
import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.command.converter.CommandAttributeConverter;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.server.IServer;
import org.heliosphere.drake.server.Server;
import org.heliosphere.drake.server.type.ServerPropertiesType;

/**
 * A standalone (not regression nor benchmarking test) test server used
 * for testing the server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class TestServer implements IApplicationListener
{
	private static TestServer test = new TestServer();

	private static IServer server = null;

	/**
	 * Launches the test server.
	 * <p>
	 * @param arguments Arguments provided on the command line.
	 */
	public static void main(final String[] arguments)
	{
		server = new Server();

		// So that the test program can be notified of server events ... if necessary ;)
		server.addListener(test);

		// Necessary for some special commands.
		ICommandManager manager = Manager.getManager(ICommandManager.class);
		manager.addConverter(new CommandAttributeConverter());

		// Run the server using a terminal so that we can enter commands and see what's happen.
		server.run(ApplicationRunType.Terminal);

		// The server and the test program will run until we enter the 'exit' command...
	}

	@Override
	public void onQuit(final Enum<? extends IApplicationExitCodeType> code)
	{
	}

	@SuppressWarnings("nls")
	@Override
	public void onStart()
	{
		server.getTerminal().getDevice().printf(
				"\n%s v.%s (%s) by %s",
				server.getProperty(ServerPropertiesType.ApplicationName),
				server.getProperty(ApplicationPropertiesType.ApplicationVersion),
				server.getProperty(ApplicationPropertiesType.ApplicationLocale),
				server.getProperty(ApplicationPropertiesType.ApplicationCopyright));
	}
}
