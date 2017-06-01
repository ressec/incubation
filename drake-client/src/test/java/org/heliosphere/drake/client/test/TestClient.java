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
package org.heliosphere.drake.client.test;

import org.heliosphere.drake.base.application.IApplicationListener;
import org.heliosphere.drake.base.application.type.ApplicationPropertiesType;
import org.heliosphere.drake.base.application.type.ApplicationRunType;
import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.command.converter.CommandAttributeConverter;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.client.Client;

/**
 * A standalone (not regression nor benchmarking test) test client used
 * for testing a client.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class TestClient extends Client implements IApplicationListener
{
	/**
	 * Launches the test client.
	 * <p>
	 * @param arguments Arguments provided on the command line.
	 */
	public static void main(final String[] arguments)
	{
		TestClient client = new TestClient();

		// So that the test program can be notified of client events ... if necessary ;)
		client.addListener(client);

		// Necessary for some special commands.
		ICommandManager manager = Manager.getCommandManager();
		manager.addConverter(new CommandAttributeConverter());

		// Run the client using a terminal so that we can enter commands and see what's happen.
		client.run(ApplicationRunType.Terminal);

		// The client and the test program will run until we enter the 'exit' command...
	}

	/**
	 * Creates a new test client application.
	 */
	public TestClient()
	{
		super();
	}

	@Override
	public void onQuit(final Enum<? extends IApplicationExitCodeType> code)
	{
	}

	@SuppressWarnings("nls")
	@Override
	public void onStart()
	{
		getTerminal().getDevice().printf(
				"\n%s v.%s (%s) by %s",
				getProperty(ApplicationPropertiesType.ApplicationName),
				getProperty(ApplicationPropertiesType.ApplicationVersion),
				getProperty(ApplicationPropertiesType.ApplicationLocale),
				getProperty(ApplicationPropertiesType.ApplicationCopyright));
	}
}
