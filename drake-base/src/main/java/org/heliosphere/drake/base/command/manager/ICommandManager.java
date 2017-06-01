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
package org.heliosphere.drake.base.command.manager;

import java.util.List;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.annotation.ICommandInfo;
import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.converter.IInputConverter;
import org.heliosphere.drake.base.command.token.TokenException;
import org.heliosphere.drake.base.manager.IManager;
import org.heliosphere.drake.base.terminal.ITerminal;

/**
 * Interface defining the behavior of a command manager.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandManager extends IManager
{
	/**
	 * Registers the given command object.
	 * <p>
	 * @param command Command to register.
	 * @param application Parent application;
	 * @throws CommandManagerException Thrown if an error occurs while trying to
	 * register a command.
	 */
	void registerCommand(final Object command, final IApplication application) throws CommandManagerException;

	/**
	 * Registers a command.
	 * <p>
	 * @param command Command to unregister.
	 * @throws CommandManagerException Thrown if an error occurs while trying to
	 * register a command.
	 */
	void unregisterCommand(final Object command) throws CommandManagerException;

	/**
	 * Finds a command according to its name or one of its alias.
	 * <p>
	 * @param name Command name or alais.
	 * @return {@link IMetaCommand} or {@code null} if no command has been found
	 * corresponding to the given command name or alias.
	 * @throws TokenException Thrown if an error occurs with command name while processing 
	 * the command.
	 * @throws CommandManagerException Thrown if an error occured while trying to find the command.
	 */
	IMetaCommand findCommand(final String name) throws TokenException, CommandManagerException;

	/**
	 * Processes the given meta command.
	 * <p>
	 * @param command Meta command to process.
	 * @return Result of the execution of the command. If {@code null}, it means
	 * the command returns {@code void}.
	 * @throws CommandManagerException Thrown if an error occurs while processing the
	 * command.
	 * @throws TokenException Thrown if an error occurs with command arguments
	 * while processing the command.
	 */
	Object processCommand(final IMetaCommand command) throws CommandManagerException, TokenException;

	/**
	 * Returns the command information for all registered commands.
	 * <p>
	 * @param prefix Command prefix. Can be {@code null}.
	 * @return List of {@link ICommandInfo}.
	 */
	List<ICommandInfo> getCommandInfo(final String prefix);

	/**
	 * Adds a command parameter converter.
	 * <p>
	 * @param converter {@link IInputConverter} being the command parameter
	 * converter to add.
	 */
	void addConverter(final IInputConverter converter);

	/**
	 * Removes a command parameter converter.
	 * <p>
	 * @param converter {@link IInputConverter} being the command parameter
	 * converter to remove.
	 */
	void removeConverter(final IInputConverter converter);

	/**
	 * Returns the terminal used to enter commands.
	 * <p>
	 * @return Terminal.
	 */
	ITerminal getTerminal();
}
