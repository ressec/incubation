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
package org.heliosphere.drake.client.type;

import org.heliosphere.drake.base.resource.bundle.IBundle;
import org.heliosphere.drake.client.resource.bundle.BundleClient;

/**
 * Enumeration of the client property types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ClientPropertiesType implements IClientPropertiesType
{
	/**
	 * Property defining the server hostname to connect to.
	 */
	ServerHostName(BundleClient.PropertyServerHostname),

	/**
	 * Property defining the server portnumber to connect to.
	 */
	ServerPortNumber(BundleClient.PropertyServerPort);

	/**
	 * Property key.
	 */
	private Enum<? extends IBundle> key = null;

	/**
	 * Creates a new property.
	 * <p>
	 * @param key Property key.
	 */
	private ClientPropertiesType(final Enum<? extends IBundle> key)
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
	 * @return {@link ClientPropertiesType}.
	 * @throws IllegalArgumentException Throws when the given property string
	 * does
	 * not correspond to a known property enumerated value.
	 */
	@SuppressWarnings("nls")
	public static ClientPropertiesType from(final String property) throws IllegalArgumentException
	{
		if (property == null || property.isEmpty())
		{
			throw new IllegalArgumentException("Property cannot be null or empty");
		}

		for (final ClientPropertiesType p : values())
		{
			if (property.equals(p.getValue()))
			{
				return p;
			}
		}

		throw new IllegalArgumentException("Error due to [cause=Cannot create a " + ClientPropertiesType.class.getSimpleName() + " from given property, property=" + property + "]");
	}
}
