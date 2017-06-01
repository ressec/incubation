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
package org.heliosphere.drake.base.command.annotation;

import java.lang.reflect.Method;

import org.heliosphere.drake.base.command.CommandException;

/**
 * Interface defining the behavior of a meta-command. A meta command is a
 * command created from a method annotated with the {@code Command} annotation.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMetaCommand
{
	/**
	 * Returns the command public information.
	 * <p>
	 * @return Command public information.
	 */
	ICommandInfo getInfo();

	/**
	 * Returns the method this meta command is built from.
	 * <p>
	 * @return {@link Method}.
	 */
	Method getMethod();

	/**
	 * Returns the command handler.
	 * <p>
	 * @return Command handler.
	 */
	Object getHandler();

	/**
	 * Returns the command parameters.
	 * <p>
	 * @return Array of {@link IMetaParameter}.
	 */
	IMetaParameter[] getParameters();

	/**
	 * Sets the values of the command parameters.
	 * <p>
	 * @param values Array of {@link Object} representing the values of the
	 * command parameters.
	 */
	void setValues(final Object[] values);

	/**
	 * Invokes the execution of the underlying command.
	 * <p>
	 * @return Result of the execution.
	 * @throws CommandException Thrown if an error occurs while invoking the
	 * underlying command.
	 */
	Object invoke() throws CommandException;
}
