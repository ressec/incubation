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
package org.heliosphere.drake.server.type;

import org.heliosphere.drake.base.resource.bundle.IBundle;
import org.heliosphere.drake.server.resource.bundle.BundleServer;

/**
 * Enumeration of the server property types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ServerPropertiesType implements IServerPropertiesType
{
	/**
	 * Property defining the name of the {@code SGS} server name.
	 */
	ApplicationName(BundleServer.PropertyApplicationName),

	/**
	 * Property defining the {@code SGS} listen port number.
	 */
	PortNumber(BundleServer.PropertyPortNumber),

	/**
	 * Property defining the name of the {@code SGS} application listener.
	 */
	ApplicationListener(BundleServer.PropertyApplicationListener),

	/**
	 * Property defining the path of the {@code SGS} application root directory.
	 */
	ApplicationRoot(BundleServer.PropertyApplicationRoot),

	/**
	 * Property defining the name of the {@code SGS} services.
	 */
	Services(BundleServer.PropertyServices),

	/**
	 * Property defining the name of the {@code SGS} managers.
	 */
	Managers(BundleServer.PropertyManagers),

	/**
	 * Property defining the name of the {@code SGS} task delay.
	 */
	TaskDelay(BundleServer.PropertyTaskDelay),

	/**
	 * Property defining the name of the {@code SGS} task period.
	 */
	TaskPeriod(BundleServer.PropertyTaskPeriod),

	/**
	 * Property defining the name of the {@code SGS} transaction timeout.
	 */
	TransactionTimeout(BundleServer.PropertyTransactionTimeout),

	/**
	 * Property defining the fully qualified class name of the message protocol.
	 */
	MessageProtocolClassname(BundleServer.PropertyMessageProtocolClassname),

	/**
	 * Property defining the message encoder type.
	 */
	MessageEncoderType(BundleServer.PropertyMessageEncoderType),

	/**
	 * Property defining the fully qualified name of the hosted game.
	 */
	GameClassname(BundleServer.PropertyGameClassname),

	/**
	 * Property defining the name of the hosted game.
	 */
	GameName(BundleServer.PropertyGameName);

	/**
	 * Property key.
	 */
	private Enum<? extends IBundle> key = null;

	/**
	 * Creates a new property.
	 * <p>
	 * @param key Property key.
	 */
	private ServerPropertiesType(final Enum<? extends IBundle> key)
	{
		this.key = key;
	}

	@Override
	public String getKey()
	{
		return ((IBundle) key).getKey();
	}

	@Override
	public String getValue()
	{
		return ((IBundle) key).getValue();
	}

	/**
	 * Creates an application property (enumerated value) from a given
	 * string (representing the property in the properties file).
	 * @param property Property string representatuon.
	 * @return {@link ServerPropertiesType}.
	 * @throws IllegalArgumentException Throws when the given property string
	 * does
	 * not correspond to a known property enumerated value.
	 */
	@SuppressWarnings("nls")
	public static ServerPropertiesType from(final String property) throws IllegalArgumentException
	{
		if (property == null || property.isEmpty())
		{
			throw new IllegalArgumentException("Property cannot be null or empty");
		}

		for (final ServerPropertiesType p : values())
		{
			if (property.equals(p.getValue()))
			{
				return p;
			}
		}

		throw new IllegalArgumentException("Error due to [cause=Cannot create a " + ServerPropertiesType.class.getSimpleName() + " from given property, property=" + property + "]");
	}
}
