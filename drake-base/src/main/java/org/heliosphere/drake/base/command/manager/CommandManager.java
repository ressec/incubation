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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.AbstractCommand;
import org.heliosphere.drake.base.command.annotation.Command;
import org.heliosphere.drake.base.command.annotation.ICommandInfo;
import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.annotation.MetaCommand;
import org.heliosphere.drake.base.command.converter.IInputConverter;
import org.heliosphere.drake.base.command.converter.InputConversionEngine;
import org.heliosphere.drake.base.command.token.Token;
import org.heliosphere.drake.base.command.token.TokenException;
import org.heliosphere.drake.base.terminal.ITerminal;
import org.heliosphere.drake.base.terminal.ITerminalListener;
import org.heliosphere.drake.base.terminal.Terminal;
import org.heliosphere.drake.utility.time.Chronometer;
import org.heliosphere.drake.utility.time.TimeHelper;

import lombok.extern.log4j.Log4j;

/**
 * The command manager class provides access to the command system of an
 * application.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class CommandManager implements ICommandManager, ITerminalListener
{
	/**
	 * Collection of registered commands.
	 */
	private Map<String, IMetaCommand> commands = new HashMap<>();

	/**
	 * Collection of registered aliases and names.
	 */
	private Map<String, String> aliases = new HashMap<>();

	/**
	 * Command parameter conversion engine.
	 */
	private InputConversionEngine inputConverter = new InputConversionEngine();

	/**
	 * Terminal.
	 */
	private ITerminal terminal = null;

	/**
	 * Registers the given command class.
	 * <p>
	 * @param commandClass Command class to register.
	 * @param application Parent application.
	 * @throws CommandManagerException Thrown if an error occurs while registering the command class.
	 */
	@SuppressWarnings("nls")
	private final void registerCommandClass(final Class<? extends AbstractCommand> commandClass, final IApplication application) throws CommandManagerException
	{
		Validate.notNull(commandClass, "Command class cannot be null");
		Validate.notNull(application, "Application cannot be null");

		Constructor<?> ctor = null;
		Object command = null;

		try
		{
			ctor = commandClass.getConstructor(IApplication.class);
			command = ctor.newInstance(application);

			for (Method method : command.getClass().getMethods())
			{
				Command annotation = method.getAnnotation(Command.class);
				if (annotation != null)
				{
					register(method, command);
				}
			}
		}
		catch (Exception e)
		{
			throw new CommandManagerException(e.getMessage());
		}
	}

	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public final void registerCommand(final Object command, final IApplication application) throws CommandManagerException
	{
		Validate.notNull(command, "Command or command class cannot be null");
		Validate.notNull(application, "Owner application cannot be null");

		if (command instanceof Class)
		{
			registerCommandClass((Class<? extends AbstractCommand>) command, application);
		}
		else
		{
			for (Method method : command.getClass().getMethods())
			{
				Command annotation = method.getAnnotation(Command.class);
				if (annotation != null)
				{
					register(method, command);
				}
			}
		}
	}

	@SuppressWarnings("nls")
	@Override
	public final Object processCommand(final IMetaCommand command) throws CommandManagerException
	{
		Object result = null;

		Chronometer time = new Chronometer();
		try
		{
			result = command.invoke();
			time.stop();
			log.info("Command processed [name=" + command.getInfo().getName() + ", execution=" + TimeHelper.nano2Milli(time.getTime()) + ", unit=millisecond]");
		}
		catch (Exception e)
		{
			throw new CommandManagerException(e.getMessage());
		}

		return result;
	}

	@SuppressWarnings("nls")
	@Override
	public final IMetaCommand findCommand(final String text) throws CommandManagerException
	{
		IMetaCommand command = null;

		List<Token> tokens = Token.tokenize(text);
		if (tokens.size() > 0)
		{
			command = getCommand(tokens.get(0).getString());

			if (command != null)
			{
				Object[] parameters;

				try
				{
					parameters = inputConverter.convertToParameters(tokens, command);
					command.setValues(parameters);
				}
				catch (TokenException e)
				{
					throw new CommandManagerException(e.getMessage());
				}
			}
			else
			{
				throw new CommandManagerException("Unknown command: " + tokens.get(0).getString());
			}
		}

		return command;
	}

	@Override
	public final List<ICommandInfo> getCommandInfo(final String prefix)
	{
		List<ICommandInfo> list = new ArrayList<>();

		for (IMetaCommand command : getCommands(prefix))
		{
			list.add(command.getInfo());
		}

		return list;
	}

	@Override
	public void unregisterCommand(final Object command) throws CommandManagerException
	{
	}

	@Override
	public void onCommand(final IMetaCommand command)
	{
	}

	@Override
	public void onException(final Exception e)
	{
	}

	@SuppressWarnings("nls")
	@Override
	public final ITerminal getTerminal()
	{
		if (terminal == null)
		{
			terminal = new Terminal();
			log.info("Terminal created");
			terminal.addListener(this);
		}

		return terminal;
	}

	@SuppressWarnings("nls")
	@Override
	public void addConverter(final IInputConverter converter)
	{
		Validate.notNull(converter, "Input converter cannot be null");

		inputConverter.addConverter(converter);
		log.info("Command converter registered [class=" + converter.getClass().getSimpleName() + "]");
	}

	@SuppressWarnings("nls")
	@Override
	public void removeConverter(final IInputConverter converter)
	{
		Validate.notNull(converter, "Input converter cannot be null");

		inputConverter.removeConverter(converter);
		log.info("Command converter unregistered [class=" + converter.getClass().getSimpleName() + "]");
	}

	/**
	 * Returns the collection of all the registered commands.
	 * <p>
	 * @param prefix Command prefix. Can be {@code null}.
	 * @return Collection of registered commands.
	 */
	public Collection<IMetaCommand> getCommands(final String prefix)
	{
		Collection<IMetaCommand> collection = new ArrayList<>();

		for (IMetaCommand command : commands.values())
		{
			if (prefix != null)
			{
				if (command.getInfo().getPrefix().equals(prefix))
				{
					collection.add(command);
				}
			}
			else
			{
				collection.add(command);
			}
		}

		return collection;
	}

	/**
	 * Registers a command.
	 * <p>
	 * @param method Method implementing the command. This method must be
	 * annotated with the {@code @Command} annotation.
	 * @param handler Command handler.
	 * @throws CommandManagerException Thrown if an error occurs while trying to
	 * register the
	 * command.
	 */
	@SuppressWarnings("nls")
	private void register(final Method method, final Object handler) throws CommandManagerException
	{
		String conflictingName = null;
		String name = null;
		IMetaCommand command = null;

		Validate.notNull(handler, "Command handler cannot be null");
		Validate.notNull(method, "Command method cannot be null");

		Command annotation = method.getAnnotation(Command.class);
		Validate.notNull(annotation, "A command must be annotated with the @Command annotation");

		name = annotation.name().isEmpty() ? method.getName() : annotation.name();
		if (!annotation.prefix().isEmpty())
		{
			name = annotation.prefix() + name;
		}

		if (isCommandNameAvailable(name, annotation.aliases(), conflictingName))
		{
			command = new MetaCommand(handler, method, annotation.prefix(), name);
			command.getInfo().setDescription(annotation.description().isEmpty() ? "n/a" : annotation.description());
			command.getInfo().setAuthor(annotation.author().isEmpty() ? "n/a" : annotation.author());
			command.getInfo().setVersion(annotation.version().isEmpty() ? "n/a" : annotation.version());

			commands.put(name, command); // Register the command.
			aliases.put(name, name); // Register the command name.

			if (annotation.aliases() != null)
			{
				for (String alias : annotation.aliases())
				{
					if (!alias.isEmpty())
					{
						aliases.put(!annotation.prefix().isEmpty() ? annotation.prefix() + alias : alias, name); // Register the command aliases.
					}
				}
			}

			log.info("Command registered [name=" + name + ", class=" + handler.getClass().getSimpleName() + "]");
		}
		else
		{
			throw new CommandManagerException("Command already registered [command=" + annotation.name() + ", alias(es)=" + Arrays.toString(annotation.aliases()) + "]");
		}
	}

	/**
	 * Returns if the given command name is available? To be avaialble, a
	 * command name must not already be registered nor all of its aliases.
	 * <p>
	 * @param commandName Command name.
	 * @param commandAliases Command aliases.
	 * @param conflictingName Will be filled with the confliting name or alias
	 * if the command name is not available.
	 * @return {@code True} if another command is already registered with the
	 * given command name or one of the command alias, {@code false} otherwise.
	 */
	private boolean isCommandNameAvailable(final String commandName, final String[] commandAliases, final String conflictingName)
	{
		if (aliases.isEmpty())
		{
			return true;
		}

		for (String alias : aliases.keySet())
		{
			if (alias.equalsIgnoreCase(commandName))
			{
				return false;
			}

			for (String e : commandAliases)
			{
				if (!alias.isEmpty() && alias.equalsIgnoreCase(e))
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns a command given its name or one of its alias.
	 * <p>
	 * @param name Command name or alias.
	 * @return {@link IMetaCommand} or {@code null} if no command has been found
	 * with the given name or alias.
	 */
	public IMetaCommand getCommand(final String name)
	{
		if (aliases.containsKey(name))
		{
			return commands.get(aliases.get(name));
		}

		return null;
	}
}