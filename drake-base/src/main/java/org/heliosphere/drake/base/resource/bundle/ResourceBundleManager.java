/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.resource.bundle;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.Synchronized;
import lombok.extern.log4j.Log4j;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.heliosphere.drake.base.exception.InvalidArgumentException;

/**
 * A manager to access resource bundles.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public class ResourceBundleManager
{
	/**
	 * Default resource bundle properties filename.
	 */
	@SuppressWarnings("nls")
	private static final String DEFAULT_INITIALIZATION_FILE = "base-bundle.properties";

	/**
	 * Bundle filename enumerated value.
	 */
	@SuppressWarnings("nls")
	private static final String BUNDLE_FILENAME = "BundleFilename";

	/**
	 * Collection of resource bundles by resource bundle class.
	 */
	private final static Map<Class<? extends IBundle>, ResourceBundle> BUNDLES = new HashMap<>(1, 0.75f);

	/**
	 * Collection of resource bundle names by resource bundle class.
	 */
	private final static Map<Class<? extends IBundle>, String> NAMES = new HashMap<>(1, 0.75f);

	/**
	 * English locale.
	 */
	@SuppressWarnings("nls")
	private final static Locale english = new Locale("en", "US");

	/**
	 * Locale of manager (set to english by default).
	 */
	private static Locale locale = english;

	/**
	 * Configuration property file.
	 */
	private static PropertiesConfiguration properties = null;

	/**
	 * Is the manager initialized?
	 */
	private static boolean isInitialized = false;

	/**
	 * Returns a resource string from its key using an enumerated value.
	 * <p>
	 * @param key Resource string key (enumerated value from an enumeration
	 * implementing the {@code IBundle} interface).
	 * @return Resource string.
	 */
	@Synchronized
	public final static String getMessage(final Enum<? extends IBundle> key)
	{
		return getMessage(key, (Object[]) null);
	}

	/**
	 * Returns a message from a resource bundle file handled by the resource
	 * bundle manager based on a given enumerated value representing the
	 * resource key of the message to retrieve.
	 * <p>
	 * @param key Enumerated resource key.
	 * @param parameters Parameters to inject in the message during message
	 * formatting.
	 * @return Message associated to the resource key or an exception message if
	 * the corresponding resource string cannot be loaded.
	 */
	@Synchronized
	public final static String getMessage(final Enum<? extends IBundle> key, final Object... parameters)
	{
		if (key == null)
		{
			throw new InvalidArgumentException(BundleBase.ResourceBundleInvalidKey);
		}

		return retrieve(key, parameters);
	}

	/**
	 * Do a default initialization of the resource bundle manager.
	 * <p>
	 * The default {@code Locale} used is {@code en_US}.
	 * @throws ResourceBundleException Thrown if the initialization of the
	 * resource bundle manager has failed.
	 */
	private final static void initialize() throws ResourceBundleException
	{
		initialize(DEFAULT_INITIALIZATION_FILE, locale);
	}

	/**
	 * Initializes the resource bundle manager with a custom resource bundle
	 * properties file and with a specific language.
	 * <p>
	 * @param filename Path and name of the property file containing
	 * configuration for the resource bundles.
	 * @param locale {@link Locale} to use for the language.
	 * @throws ResourceBundleException Thrown if a resource bundle file cannot
	 * be found.
	 */
	private final static void initialize(final String filename, final Locale locale) throws ResourceBundleException
	{
		if ((filename == null) || (filename.length() == 0))
		{
			throw new InvalidArgumentException(BundleBase.ResourceBundleInvalidName);
		}

		if (locale == null)
		{
			throw new InvalidArgumentException(BundleBase.ResourceBundleInvalidLocale);
		}

		synchronized (BUNDLES)
		{
			try
			{
				setProperties(new PropertiesConfiguration(filename));
				setLocale(locale);
				register(properties);
			}
			catch (final ConfigurationException e)
			{
				setInitialized(false);
				throw new ResourceBundleException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Sets if the resource bundle manager is initialized.
	 * <p>
	 * @param value {@code True} to set the resource bundle manager as being
	 * initialized, {@code false} otherwise.
	 */
	private final static void setInitialized(final boolean value)
	{
		isInitialized = value;
	}

	/**
	 * Sets the properties file.
	 * <p>
	 * @param properties New {@link PropertiesConfiguration} file to use.
	 */
	private final static void setProperties(final PropertiesConfiguration properties)
	{
		ResourceBundleManager.properties = properties;
	}

	/**
	 * Registers a resource bundle using a resource bundle enumeration class
	 * (Implementing the {@link IBundle} interface).
	 * <p>
	 * @param bundleEnumClass Resource bundle enumeration class.
	 */
	@Synchronized
	public final static void register(final Class<? extends IBundle> bundleEnumClass)
	{
		if (!isInitialized)
		{
			initialize();
		}

		IBundle value = bundleEnumClass.getEnumConstants()[0]; // BUNDLE_FILENAME must be at index 0!
		if (value.toString().equals(BUNDLE_FILENAME))
		{
			String filename = value.getKey();

			final ResourceBundle bundle = ResourceBundle.getBundle(filename, locale);

			// Ensure the loaded bundle is for the required language.
			if (bundle.getLocale().getISO3Language().equals(locale.getISO3Language()))
			{
				register(bundleEnumClass, bundle);
			}
			else
			{
				throw new ResourceBundleException(BundleBase.ResourceBundleError, filename, locale);
			}
		}
	}

	/**
	 * Registers a new resource bundle and add it to the collection of resource
	 * bundles already managed by the resource bundle manager.
	 * <p>
	 * @param filename Bundle properties configuration file to append.
	 * @throws ResourceBundleException Thrown if the resource bundle file cannot
	 * be found.
	 */
	@Synchronized
	public final static void register(final String filename) throws ResourceBundleException
	{
		if (!isInitialized)
		{
			initialize();
		}

		try
		{
			register(new PropertiesConfiguration(filename));

			if (properties != null)
			{
				properties.append(properties);
			}
			else
			{
				setProperties(properties);
			}
		}
		catch (final ConfigurationException e)
		{
			throw new ResourceBundleException(BundleBase.ResourceBundleNotFound, filename, getLocale(), e);
		}
	}

	/**
	 * Registers the resource bundles defined in a given properties file.
	 * <p>
	 * @param properties {@link PropertiesConfiguration} file containing the
	 * resource bundles to register.
	 */
	@SuppressWarnings({ "nls", "unchecked" })
	private final static void register(final PropertiesConfiguration properties)
	{
		if (properties == null)
		{
			throw new InvalidArgumentException(BundleBase.ResourceBundleInvalidConfiguration);
		}

		String value = null;
		String name = null;

		final Iterator<String> iter = properties.getKeys();
		while ((iter != null) && iter.hasNext())
		{
			value = iter.next();
			name = properties.getString(value);

			final ResourceBundle bundle = ResourceBundle.getBundle(name, locale);

			// Ensure the loaded bundle is for the required language.
			if (bundle.getLocale().getLanguage().equals(locale.getLanguage()))
			{
				try
				{
					register((Class<? extends IBundle>) Class.forName(value), bundle);
				}
				catch (ClassNotFoundException e)
				{
					// Cannot get resource bundle, so message must be in raw format.
					throw new ResourceBundleException(
							MessageFormat.format(
									"Cannot register properties [file={0}, class={1}, reason={2}, resolution={3}]",
									name,
									value,
									"The given class cannot be found",
									"Ensure the given class exist as an enumeration class and implements the IBundle interface"));
				}
			}
			else
			{
				// Cannot get resource bundle, so message must be in raw format.
				throw new ResourceBundleException(
						MessageFormat.format(
								"Bundle cannot be found [name={0}, locale={1}]", name, locale));
			}
		}
	}

	/**
	 * Registers a resource bundle.
	 * <p>
	 * @param bundleClass Class of the resource bundle to register.
	 * @param bundle Resource bundle to register.
	 * @throws ResourceBundleException Thrown if an error occured while trying
	 * to register a resource bundle.
	 */
	private final static void register(final Class<? extends IBundle> bundleClass, final ResourceBundle bundle) throws ResourceBundleException
	{
		ResourceBundle previous = null;

		try
		{
			if (!BUNDLES.containsKey(bundleClass))
			{
				BUNDLES.put(bundleClass, bundle);
				NAMES.put(bundleClass, bundleClass.getEnumConstants()[0].getKey());

				setInitialized(true);

				log.debug(getMessage(BundleBase.ResourceBundleRegistered, bundleClass.getSimpleName(), locale.toString(), Integer.valueOf(bundle.keySet().size())));
			}
			else
			{
				/*
				 * Case where the bundle class already exists. If the locale is
				 * the same, do nothing but if the locale is different then
				 * replace it with the new one.
				 */
				previous = BUNDLES.get(bundleClass);
				if (previous.getLocale().equals(bundle.getLocale()))
				{
					log.debug(getMessage(BundleBase.ResourceBundleAlreadyRegistered, bundleClass.getSimpleName(), locale.toString()));
				}
				else
				{
					BUNDLES.put(bundleClass, bundle);

					log.debug(getMessage(BundleBase.ResourceBundleReplaced, bundleClass.getSimpleName(), previous.getLocale().toString(), locale.toString(), Integer.valueOf(bundle.keySet().size())));
				}
			}
		}
		catch (final Exception e)
		{
			log.error(e.getMessage(), e);
			throw (ResourceBundleException) e;
		}
	}

	/**
	 * Retrieves a message from a resource bundle from its key.
	 * <p>
	 * @param key Resource key to retrieve.
	 * @param parameters Parameters to inject while formatting the message.
	 * @return The formatted message.
	 */
	@SuppressWarnings("nls")
	private final static String retrieve(final Enum<? extends IBundle> key, final Object... parameters)
	{
		if (!isInitialized)
		{
			initialize();
		}

		final Class<? extends IBundle> bundleClass = key.getDeclaringClass();

		if (BUNDLES.containsKey(bundleClass))
		{
			try
			{
				return MessageFormat.format(BUNDLES.get(bundleClass).getString(((IBundle) key).getKey()), parameters);
			}
			catch (MissingResourceException e)
			{
				throw new ResourceBundleException(BundleBase.ResourceBundleInvalidKey, bundleClass.getSimpleName(), key.name(), bundleClass.getEnumConstants()[0].getKey(), getLocale(), e);
			}
		}

		return "Resource bundle key cannot be found [bundle=" + bundleClass.getName() + ", key=" + key.name() + "]";
	}

	/**
	 * Sets the language used by the {@link ResourceBundleManager}.
	 * <p>
	 * <b>Note:</b> Calling this service forces the
	 * {@link ResourceBundleManager} to reload all the resource bundle files
	 * according to the new locale.
	 * <p>
	 * @param locale {@link Locale} corresponding to the new language to set.
	 */
	@Synchronized
	public final static void setLocale(final Locale locale)
	{
		if (locale.getLanguage() != ResourceBundleManager.locale.getLanguage())
		{
			refresh(locale);
		}
	}

	/**
	 * Refresh all the resource bundles already handled by the
	 * {@link ResourceBundleManager} for the given {@link Locale}.
	 * <p>
	 * @param locale {@link Locale} corresponding to the new language to use.
	 */
	@SuppressWarnings("nls")
	private final static void refresh(final Locale locale)
	{
		if (NAMES.isEmpty())
		{
			ResourceBundleManager.locale = locale;
		}

		for (Class<? extends IBundle> bundleClass : NAMES.keySet())
		{
			final ResourceBundle bundle = ResourceBundle.getBundle(NAMES.get(bundleClass), locale);
			if ((bundle != null) && (bundle.getLocale().getLanguage() != locale.getLanguage()))
			{
				log.error("Bundle cannot be found [name=" + NAMES.get(bundleClass) + ", locale=" + locale.toString() + "]. Using default one [name=" + NAMES.get(bundleClass) + ", locale=" + getLocale().toString() + "]");
				ResourceBundleManager.locale = english;
			}
			else
			{
				ResourceBundleManager.locale = locale;
			}

			BUNDLES.clear();
			register(bundleClass, bundle);
		}
	}

	/**
	 * Returns a copy of the locale used by the {@link ResourceBundleManager}.
	 * <p>
	 * @return {@link Locale}
	 */
	public final static Locale getLocale()
	{
		return new Locale(locale.getLanguage(), locale.getCountry(), locale.getVariant());
	}
}
