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

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.command.CommandException;

/**
 * A meta command entered using a terminal.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Getter
@Setter
public final class MetaCommand implements IMetaCommand
{
	/**
	 * Command information.
	 */
	private ICommandInfo info;

	/**
	 * Command method handler.
	 */
	private Method method;

	/**
	 * Command handler.
	 */
	private Object handler;

	/**
	 * Command parameters.
	 */
	private IMetaParameter[] parameters;

	/**
	 * Parameter values for command invocation.
	 */
	private Object[] values;

	/**
	 * Creates a new meta command.
	 * <p>
	 * @param handler Command handler.
	 * @param method Method declaring the command.
	 * @param prefix Command prefix.
	 * @param name Command name.
	 */
	@SuppressWarnings("nls")
	public MetaCommand(final Object handler, final Method method, final String prefix, final String name)
	{
		Validate.notNull(handler, "Command handler cannot be null");
		Validate.notNull(method, "Command method cannot be null");
		Validate.notNull(name, "Command name cannot be null");

		parameters = MetaParameter.forMethod(method);

		assert (parameters.length == method.getParameterTypes().length);

		info = new CommandInfo();
		info.setName(name);
		info.setPrefix(prefix);
		info.setArity(parameters.length);

		this.method = method;
		this.handler = handler;
	}

	/**
	 * Invokes the command.
	 * <p>
	 * @return Result of the execution of the command or {@code null} if the
	 * command returns {@code void}.
	 * @throws CommandException Thrown if an error occurs while invoking the
	 * command.
	 */
	@Override
	@SuppressWarnings("nls")
	public final Object invoke() throws CommandException
	{
		Validate.notNull(method, "Method cannot be null");

		try
		{
			return method.invoke(handler, this.getValues());
		}
		catch (Exception e)
		{
			throw new CommandException(e.getMessage());
		}
	}
}
