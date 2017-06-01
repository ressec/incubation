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
package org.heliosphere.drake.base.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.command.manager.CommandManager;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.message.manager.IMessageManager;
import org.heliosphere.drake.base.message.manager.MessageManager;

/**
 * High-level manager providing access to domain specific
 * managers such as the {@code Resourcemanager}, the {@code MessageManager},
 * the {@code CommandManager} and so on.
 * <p>
 * <b>Note:</b><br>
 * As a singleton is unique per {@code JVM - Java Virtual Machine}, two
 * applications running on the same machine will have access to their own
 * version of the manager.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2012
 * @version 1.0.0
 */
@Log4j
public final class Manager
{
	/**
	 * Domain specific mamagers.
	 */
	private static Map<Class<? extends IManager>, Object> managers = new HashMap<>();

	/**
	 * Application.
	 */
	private static IApplication application = null;

	/**
	 * Instance of the Drake manager.
	 */
	@SuppressWarnings("unused")
	private static Manager instance = new Manager();

	/**
	 * Creates a new {@code Relic} manager.
	 */
	private Manager()
	{
		addManager(new CommandManager());
		addManager(new MessageManager());
	}

	/**
	 * Adds the given manager as a registered manager.
	 * <p>
	 * @param manager Manager to register. Must be a {@code Drake} derived
	 * manager.
	 */
	@SuppressWarnings("nls")
	public final static void addManager(final IManager manager)
	{
		Validate.notNull(manager, "Manager cannot be null");

		if (!managers.containsKey(manager.getClass()))
		{
			// Special case of the message server manager that should replace the standard message manager.
			if (managers.containsKey(IMessageManager.class) && IMessageManager.class.isAssignableFrom(manager.getClass()))
			{
				managers.remove(IMessageManager.class);
				managers.put(IMessageManager.class, manager);
			}
			else
			{
				managers.put(manager.getClass(), manager);
			}
		}
	}

	public final static boolean replaceManager(final Class<? extends IManager> managerClass, final IManager manager)
	{
		boolean replaced = false;
		Class<? extends IManager> key = null;

		Validate.notNull(manager, "Manager cannot be null");
		Validate.isTrue(managerClass.isAssignableFrom(manager.getClass()), "Manager " + manager.getClass().getName() + " must be assignable from: " + managerClass.getName());

		Iterator<Class<? extends IManager>> iter = managers.keySet().iterator();
		while (iter.hasNext() && !replaced)
		{
			key = iter.next();
			if (managerClass.isAssignableFrom(key))
			{
				managers.remove(key);
				managers.put(managerClass, manager);
				replaced = true;
			}
		}

		return replaced;
	}

	/**
	 * Returns the registered application.
	 * <p>
	 * @return {@link IApplication} being the registered application.
	 */
	public final static IApplication getApplication()
	{
		return application;
	}

	/**
	 * Returns the manager of the given type. If the given type is an interface,
	 * it will return the fist manager that implements this interface.
	 * <p>
	 * @param <T> Type of the manager.
	 * @param type Class representing the type of the manager.
	 * @return Manager of the specified type for the current application.
	 * @throws IllegalArgumentException Thrown if no manager is found for the
	 * given type.
	 */
	@SuppressWarnings("unchecked")
	public final static <T extends IManager> T getManager(final Class<? extends IManager> type)
	{
		if (type.isInterface())
		{
			for (Object o : managers.values())
			{
				if (type.isAssignableFrom(o.getClass()))
				{
					return (T) o;
				}
			}

			// TODO Try to find the first manager implementing the given interface.

		}
		else
		{
			Object o = managers.get(type);
			if (o != null)
			{
				return (T) o;
			}
		}

		throw new IllegalArgumentException("Cannot find manager of type: " + type);
	}

	/**
	 * Returns the command mananger.
	 * <p>
	 * @return {@link ICommandManager}.
	 */
	public final static ICommandManager getCommandManager()
	{
		return getManager(ICommandManager.class);
	}

	/**
	 * Returns the message mananger.
	 * <p>
	 * @return {@link IMessageManager}.
	 */
	public final static IMessageManager getMessageManager()
	{
		return getManager(IMessageManager.class);
	}

	/**
	 * Checks if the given type of manager is already registered?
	 * <p>
	 * @param type Class representing the type of the manager.
	 * @return {@code True} if the given type of manager is already registered,
	 * {@code false otherwise}.
	 */
	public final static boolean existManager(final Class<? extends IManager> type)
	{
		return managers.get(type) != null ? true : false;
	}
}