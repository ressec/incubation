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
package org.heliosphere.drake.base.application;

import java.net.InetAddress;
import java.util.Locale;
import java.util.Properties;

import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.application.type.IApplicationPropertiesType;
import org.heliosphere.drake.base.application.type.IApplicationRunType;
import org.heliosphere.drake.base.application.type.IApplicationStatusType;
import org.heliosphere.drake.base.application.type.IApplicationType;
import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.manager.CommandManager;
import org.heliosphere.drake.base.game.GameException;
import org.heliosphere.drake.base.game.IGame;
import org.heliosphere.drake.base.terminal.ITerminal;

/**
 * Interface defining the behavior of an application.
 * <p>
 * Note that you are not limited to client and server applications, using the
 * framework you can easily extend the basic model providing client and server
 * applications.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IApplication
{
	/**
	 * Returns the application name.
	 * <p>
	 * @return Application name.
	 */
	String getName();

	/**
	 * Returns the application locale.
	 * <p>
	 * @return {@link Locale}.
	 */
	Locale getLocale();

	/**
	 * Returns the application type.
	 * <p>
	 * @return Application type.
	 */
	Enum<? extends IApplicationType> getApplicationType();

	/**
	 * Returns the application status type.
	 * <p>
	 * @return Application status type.
	 */
	Enum<? extends IApplicationStatusType> getStatus();

	/**
	 * Returns the internet protocol address of the machine running this
	 * application.
	 * <p>
	 * @return The internet protocol {@code IP} address or {@code null} if no
	 * {@code IP} address can be found.
	 */
	InetAddress getInetAddress();

	/**
	 * Returns the properties used by this application.
	 * <p>
	 * @return {@link Properties}.
	 */
	Properties getProperties();

	/**
	 * Returns the value of a given property.
	 * <p>
	 * @param property Property to retrieve.
	 * @return Value of the property or {@code null} if the property has not
	 * been found.
	 */
	String getProperty(final Enum<? extends IApplicationPropertiesType> property);

	/**
	 * Returns the value of a given property.
	 * <p>
	 * @param property Property to retrieve.
	 * @return Value of the property or {@code null} if the property has not
	 * been found.
	 */
	String getProperty(final String property);

	/**
	 * Returns the game owned by the application.
	 * <p>
	 * @return {@link IGame}.
	 */
	IGame getGame();

	/**
	 * Sets the game for this application.
	 * <p>
	 * @param game Game to be set.
	 * @throws GameException Thrown if an error occured while setting the game.
	 */
	void setGame(final IGame game) throws GameException;

	/**
	 * Submits the given command.
	 * <p>
	 * @param command Command to submit.
	 */
	void submitCommand(final IMetaCommand command);

	/**
	 * Returns the terminal associated to the application. Generally this is the
	 * terminal of the {@link CommandManager}.
	 * <p>
	 * @return {@link ITerminal}.
	 */
	ITerminal getTerminal();

	/**
	 * Quits the application using a given exit code.
	 * <p>
	 * @param code Enumerated value representing the exit code type.
	 */
	void quit(final Enum<? extends IApplicationExitCodeType> code);

	/**
	 * Adds an application listener.
	 * <p>
	 * @param listener {@link IApplicationListener} to add.
	 */
	void addListener(final IApplicationListener listener);

	/**
	 * Removes an application listener.
	 * <p>
	 * @param listener {@link IApplicationListener} to remove.
	 */
	void removeListener(final IApplicationListener listener);

	/**
	 * Runs the application.
	 * <p>
	 * @param mode {@link IApplicationRunType} being the run mode to run.
	 */
	void run(final Enum<? extends IApplicationRunType> mode);
}
