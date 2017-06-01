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
package org.heliosphere.drake.base.terminal;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.terminal.device.Device;
import org.heliosphere.drake.base.terminal.device.IDevice;

/**
 * Abstract implementation of a {@link ITerminal}.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 */
@Log4j
public final class Terminal implements ITerminal
{
	/**
	 * Terminal prompt.
	 */
	@SuppressWarnings("nls")
	private String PROMPT = " \n$> ";

	/**
	 * Collection of terminal listeners that want to be notified each time a
	 * command has been entered on the terminal.
	 */
	private final List<ITerminalListener> listeners = new ArrayList<>();

	/**
	 * Underlying console.
	 */
	private IDevice console = Device.getDefaultDevice();

	/**
	 * Dedicated thread for console.
	 */
	private volatile Thread thread;

	/**
	 * Creates a new terminal in non-blocking mode by creating a dedicated
	 * thread for the console to run and wait for user input.
	 */
	@SuppressWarnings("nls")
	public Terminal()
	{
		thread = new Thread(this, getClass().getSimpleName());
		log.info("Terminal thread created [name=" + getClass().getSimpleName() + "]");
	}

	@SuppressWarnings("nls")
	@Override
	public final void run()
	{
		String text = null;
		Object result = null;
		IMetaCommand command = null;
		ICommandManager manager = Manager.getManager(ICommandManager.class);

		while (thread == Thread.currentThread())
		{
			try
			{
				console.printf("%s", PROMPT);
				text = console.readLine();

				if (text != null && !text.isEmpty())
				{
					try
					{
						command = manager.findCommand(text);
						result = manager.processCommand(command);

						if (result instanceof String)
						{
							console.printf("%", result);
						}

						notify(command); // Notify listeners a command has been entered on the console.
					}
					catch (Exception e)
					{
						console.printf("Exception: %s", e.getMessage());
					}
				}

				synchronized (this)
				{
					try
					{
						Thread.sleep(50); // Avoid consuming all the cpu!
					}
					catch (final InterruptedException e)
					{
						//						console.printf("A %s occured: %s", e.getClass().getSimpleName(), e.getMessage());
					}
				}
			}
			catch (TerminalException e)
			{
				try
				{
					console.printf("A %s occured: %s", e.getClass().getSimpleName(), e.getMessage());
				}
				catch (TerminalDisplayException e1)
				{
					log.error(e1.getMessage(), e1);
				}

				notify(e); // Notify listeners an exception occured on the console.
			}
		}
	}

	/**
	 * Notifies the terminal listeners a command has been entered. It's the
	 * responsibility of the listeners to process the command.
	 * <p>
	 * @param command {@link IMetaCommand} entered.
	 */
	@SuppressWarnings("nls")
	private final void notify(final IMetaCommand command)
	{
		for (ITerminalListener listener : listeners)
		{
			log.info("Terminal command notification [command=" + command.getInfo().getName() + ", listener=" + listener.getClass().getSimpleName() + "]");
			listener.onCommand(command);
		}
	}

	/**
	 * Notifies the terminal listeners an exception has occured.
	 * <p>
	 * @param e Exception.
	 */
	private final void notify(final Exception e)
	{
		for (ITerminalListener listener : listeners)
		{
			listener.onException(e);
		}
	}

	@SuppressWarnings("nls")
	@Override
	public void start()
	{
		if (!thread.isAlive())
		{
			try
			{
				console.printf("\n%s\n", "Type the 'help' command to get a list of the registered commands and the 'exit' command to exit this application");
			}
			catch (TerminalDisplayException e)
			{
				log.error(e.getMessage(), e);
			}
			thread.start();
			log.info("Terminal thread started [name=" + thread.getName() + "]");
		}
	}

	@SuppressWarnings("nls")
	@Override
	public void stop()
	{
		if (thread.isAlive())
		{
			Thread.currentThread().interrupt();
			log.info("Terminal thread stopped [name=" + thread.getName() + "]");
			thread = null;
		}
	}

	@SuppressWarnings("nls")
	@Override
	public void suspend()
	{
		log.info("Terminal thread suspended [name=" + thread.getName() + "]");
	}

	@SuppressWarnings("nls")
	@Override
	public void resume()
	{
		log.info("Terminal thread resumed [name=" + thread.getName() + "]");
	}

	@SuppressWarnings("nls")
	@Override
	public final void addListener(final ITerminalListener listener)
	{
		if (!listeners.contains(listener))
		{
			log.info("Terminal listener registered [class=" + listener.getClass().getSimpleName() + "]");
			listeners.add(listener);
		}
	}

	@SuppressWarnings("nls")
	@Override
	public final void removeListener(final ITerminalListener listener)
	{
		if (listeners.contains(listener))
		{
			log.info("Terminal listener unregistered [class=" + listener.getClass().getSimpleName() + "]");
			listeners.remove(listener);
		}
	}

	@Override
	public final IDevice getDevice()
	{
		return console;
	}
}
