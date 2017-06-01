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
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.type.ApplicationPropertiesType;
import org.heliosphere.drake.base.application.type.ApplicationRunType;
import org.heliosphere.drake.base.application.type.ApplicationStatusType;
import org.heliosphere.drake.base.application.type.ApplicationType;
import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.application.type.IApplicationPropertiesType;
import org.heliosphere.drake.base.application.type.IApplicationRunType;
import org.heliosphere.drake.base.application.type.IApplicationStatusType;
import org.heliosphere.drake.base.application.type.IApplicationType;
import org.heliosphere.drake.base.command.CommandException;
import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.manager.ICommandManager;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.manager.MessageManager;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.base.resource.bundle.BundleBase;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;
import org.heliosphere.drake.base.terminal.ITerminal;

import lombok.extern.log4j.Log4j;

/**
 * Abstract implementation of an application.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractApplication implements IApplication
{
	/**
	 * Properties associated to the application accessible using enumerations.
	 */
	protected Map<Enum<? extends IApplicationPropertiesType>, String> properties = new HashMap<>();

	/**
	 * Contains all the properties including those who have no enumeration
	 * associated.
	 */
	protected Map<String, String> allProperties = new HashMap<>();

	/**
	 * Application type.
	 */
	protected Enum<? extends IApplicationType> type = null;

	/**
	 * Application status type.
	 */
	protected Enum<? extends IApplicationStatusType> status = null;

	/**
	 * Locale of the application.
	 */
	protected Locale locale = null;

	/**
	 * Application listeners.
	 */
	protected Set<IApplicationListener> listeners = new HashSet<>();

	/**
	 * Configuration file(s).
	 */
	protected Configuration configuration = null;

	/**
	 * Default constructor.
	 */
	public AbstractApplication()
	{
		// Required by the {@code AppListener} interface in {@code AbstractServer}.
	}

	/**
	 * Creates a new abstract application given a properties path name.
	 * <p>
	 * @param type {@link ApplicationType} being the application type.
	 * @throws ApplicationException Thrown if an error occurs while initializing
	 * the application.
	 */
	@SuppressWarnings("nls")
	public AbstractApplication(final Enum<? extends IApplicationType> type) throws ApplicationException
	{
		String propertiesFilename = null;

		this.type = type;
		status = ApplicationStatusType.INITIALIZING;

		/*
		 * Ensure according to the application type that the corresponding
		 * system property for the configuration file as been set up.
		 */
		switch ((ApplicationType) type)
		{
		case CLIENT:
			propertiesFilename = System.getProperty("drake.client.properties");
			Validate.notNull(propertiesFilename, "When starting a client application, ensure that the system property: drake.client.properties has been set up correctly!");

			try
			{
				try
				{
					// In case of a server, the initialize service is called by the SGS framework.
					initialize(new PropertiesConfiguration(propertiesFilename));
				}
				catch (ConfigurationException e)
				{
					URL url = getClass().getResource(propertiesFilename);
					if (url != null)
					{
						initialize(new PropertiesConfiguration(url.getPath()));
					}
				}
			}
			catch (Exception e)
			{
				log.error(e.getMessage(), e);
				status = ApplicationStatusType.ERROR;
				throw new ApplicationException(e.getMessage());
			}
			break;

		case SERVER:
			try
			{
				configuration = new PropertiesConfiguration(System.getProperty("drake.server.properties"));
			}
			catch (ConfigurationException e)
			{
				log.error(e.getMessage(), e);
				status = ApplicationStatusType.ERROR;
				throw new ApplicationException(e.getMessage());
			}
			break;
		}

		status = ApplicationStatusType.INITIALIZED;
	}

	/**
	 * Initializes the application.
	 * <p>
	 * @param configuration Properties configuration file.
	 * @throws ConfigurationException Thrown if an error occurs while
	 * initializing the configuration file.
	 * @throws CommandException ???
	 */
	protected void initialize(final Configuration configuration) throws ConfigurationException, CommandException
	{
		this.configuration = configuration;

		registerResourceBundles();
		initializeProperties();
		registerCommands();
		initializePlayers();

		try
		{
			((MessageManager) Manager.getMessageManager()).initialize(configuration);
		}
		catch (MessageManagerException e)
		{
			throw new ConfigurationException(e);
		}
	}

	/**
	 * Initializes the properties of the application.
	 * <p>
	 * @throws ConfigurationException Thrown if an error occurs while
	 * initializing the configuration file.
	 */
	protected void initializeProperties() throws ConfigurationException
	{
		Iterator<String> iter = configuration.getKeys();
		while (iter.hasNext())
		{
			initializeProperty(iter.next());
		}
	}

	/**
	 * Initializes the player(s) depending on the application type.
	 */
	protected void initializePlayers()
	{
	}

	/**
	 * Initializes an application property.
	 * <p>
	 * @param property Application property string (property key).
	 */
	@SuppressWarnings("nls")
	protected void initializeProperty(final String property)
	{
		try
		{
			properties.put(ApplicationPropertiesType.from(property), configuration.getString(property));
		}
		catch (IllegalArgumentException e)
		{
			log.warn("Property: " + property + " does not seem to have an associated enumerated value");
			allProperties.put(property, configuration.getString(property));
		}
	}

	/**
	 * Initializes the necessary resource bundles.
	 */
	@SuppressWarnings("static-method")
	protected void registerResourceBundles()
	{
		ResourceBundleManager.register(BundleBase.class);
	}

	/**
	 * Register application commands.
	 * <p>
	 * @throws CommandException Exception thrown when commands cannot be
	 * registered.
	 */
	protected void registerCommands() throws CommandException
	{
	}

	@Override
	public final String getName()
	{
		return null;
	}

	@Override
	public final Enum<? extends IApplicationType> getApplicationType()
	{
		return type;
	}

	@Override
	public final Enum<? extends IApplicationStatusType> getStatus()
	{
		return status;
	}

	@Override
	public final InetAddress getInetAddress()
	{
		return null;
	}

	@Override
	public final Properties getProperties()
	{
		return null;
	}

	@Override
	public String getProperty(final Enum<? extends IApplicationPropertiesType> property)
	{
		return properties.get(property);
	}

	@SuppressWarnings("nls")
	@Override
	public String getProperty(final String key)
	{
		Validate.notNull(key, "Property key cannot be null!");

		ApplicationPropertiesType property = null;

		try
		{
			property = ApplicationPropertiesType.from(key);
		}
		catch (IllegalArgumentException e)
		{
			return allProperties.get(key);
		}

		return properties.get(property);
	}

	@Override
	public Locale getLocale()
	{
		if (locale == null)
		{
			final String value = getProperty(ApplicationPropertiesType.ApplicationLocale);
			locale = new Locale(value.substring(0, value.indexOf('_')), value.substring(value.indexOf('_') + 1, value.length()));
		}

		return locale;
	}

	@Override
	public void submitCommand(final IMetaCommand command)
	{
	}

	@Override
	public void quit(final Enum<? extends IApplicationExitCodeType> code)
	{
		status = ApplicationStatusType.STOPPING;

		for (IApplicationListener listener : listeners)
		{
			listener.onQuit(code);
		}

		getTerminal().stop();
	}

	@Override
	public final ITerminal getTerminal()
	{
		return ((ICommandManager) Manager.getManager(ICommandManager.class)).getTerminal();
	}

	@Override
	public final void addListener(final IApplicationListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public final void removeListener(final IApplicationListener listener)
	{
		listeners.remove(listener);
	}

	@Override
	public void run(final Enum<? extends IApplicationRunType> mode)
	{
		status = ApplicationStatusType.RUNNING;

		for (IApplicationListener listener : listeners)
		{
			listener.onStart();
		}

		if (mode == ApplicationRunType.Terminal)
		{
			getTerminal().start();
		}
	}
}
